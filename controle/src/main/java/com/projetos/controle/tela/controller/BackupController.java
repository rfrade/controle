package com.projetos.controle.tela.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.projetos.controle.tela.base.AbstractController;
import com.projetos.controle_negocio.exception.NegocioException;
import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_util.conversao.DateUtil;

@SuppressWarnings({ "deprecation", "restriction" })
@Controller
@Lazy
/**
 * Controller da tela de backup
 * @author Rafael
 */
// TODO: Adicionar tratamento para quando der erro ao gerar o backup,
// atualmente não existe (17/07/2017)
public class BackupController extends AbstractController {

	@Autowired
	private ParametroService parametroService;

	/**
	 * Objeto usado para controlar as threads envolvendo a geração do arquivo e
	 * a barra de progresso
	 */
	private Object controle = new Object();

	private String caminhoArquivo;
	private ControladorProgressBar controladorProgressBar;

	/**
	 * Monta a tela de backup e exibe
	 * 
	 * @param primaryStage
	 */
	public void exibirTelaBackup(Stage primaryStage) {

		try {
			validarCaminhos();
		} catch (NegocioException e) {
			exibirMensagem(e.getParametroMensagem());
			return;
		}

		Label statusLabel = new Label("");
		ProgressBar progressBar = new ProgressBar();
		Button runButton = new Button("Gerar Backup");
		progressBar.setPrefWidth(320);
		progressBar.setVisible(true);
		progressBar.setProgress(0);

		final VBox layout = VBoxBuilder
				.create()
				.spacing(8)
				.children(
						VBoxBuilder
								.create()
								.spacing(5)
								.children(
										HBoxBuilder
												.create()
												.spacing(10)
												.children(runButton,
														statusLabel).build(),
										progressBar).build()).build();
		layout.setStyle("-fx-background-color: white; -fx-padding:20; -fx-font-size: 16;");

		Stage popup = new Stage();
		popup.initOwner(primaryStage);
		popup.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(layout);
		popup.setScene(scene);
		popup.show();

		this.controladorProgressBar = new ControladorProgressBar(runButton,
				statusLabel, progressBar, popup);
		runButton.setOnAction(controladorProgressBar);
	}

	private void validarCaminhos() throws NegocioException {
		caminhoArquivo = parametroService.getCaminhoBackupArquivos().getValor();
		File caminhoBackup = new File(caminhoArquivo);

		if (!caminhoBackup.exists()) {
			throw NegocioException.criarNegocioException("backup.verifique_se_o_diretorio_existe");
		}

	}

	private class ControladorProgressBar implements EventHandler<ActionEvent> {

		private Label statusLabel = new Label("Status");
		private ProgressBar progressBar = new ProgressBar();
		private Button runButton;
		private Stage popup;

		public ControladorProgressBar(Button runButton, Label statusLabel,
				ProgressBar progressBar, Stage popup) {
			this.runButton = runButton;
			this.statusLabel = statusLabel;
			this.progressBar = progressBar;
			this.popup = popup;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void handle(ActionEvent actionEvent) {

			final ThreadBackup threadBackup = new ThreadBackup();

			statusLabel.textProperty().bind(threadBackup.messageProperty());
			runButton.disableProperty().bind(threadBackup.runningProperty());
			progressBar.progressProperty()
					.bind(threadBackup.progressProperty());
			threadBackup.stateProperty().addListener(
					new ChangeListener<Worker.State>() {
						@Override
						public void changed(
								ObservableValue<? extends Worker.State> observableValue,
								Worker.State oldState, Worker.State newState) {
							if (newState == Worker.State.SUCCEEDED) {
								runButton.setText("Salvar Arquivo");
								runButton
										.setOnAction(new ControladorFileChooser(
												popup));
							}
						}
					});

			new Thread(threadBackup).start();

		}

		public void fecharPopup() {
			/*
			 * Platform.runLater(new Runnable() {
			 * 
			 * @Override public void run() {
			 */
			popup.close();
			/*
			 * } });
			 */
		}

	}

	@SuppressWarnings("rawtypes")
	private class ThreadBackup extends Task {

		@Override
		protected ObservableList<String> call() throws InterruptedException {
			updateProgress(-1, 0);
			updateMessage("Gerando Backup . . .");

			// salva o arquivo
			synchronized (controle) {
				new Thread(new ExecutorMysqlDump()).start();
				controle.wait();

			}
			updateProgress(0, 0);
			updateMessage("Backup completo");
			return null;
		}
	}

	class ControladorFileChooser implements EventHandler<ActionEvent> {

		private Stage popup;

		public ControladorFileChooser(Stage popup) {
			this.popup = popup;
		}

		@Override
		public void handle(ActionEvent event) {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setInitialDirectory(new File("/"));
				fileChooser.setInitialFileName("backup.sql");
				File destino = fileChooser.showSaveDialog(popup);

				if (destino != null) {
					File from = new File(caminhoArquivo);
					if (from.exists()) {
						Files.copy(from.toPath(), destino.toPath());
					}
				}
			} catch (IOException e) {
				popup.close();
				tratarErro(e);
			}
		}
	}

	/**
	 * Thread que executará o mysqldum.exe
	 * 
	 * @author Rafael
	 */
	private class ExecutorMysqlDump implements Runnable {

		@Override
		public void run() {
			gerarArquivo();
		}

		public void gerarArquivo() {
			try {
				synchronized (controle) {

					String date = DateUtil.fullDateTime();
					caminhoArquivo = caminhoArquivo.concat("\\backup")
							.concat(date).concat(".sql");

					String caminhoMysqldump = parametroService
							.getCaminhoMysqldump().getValor();
					ProcessBuilder pb = new ProcessBuilder(caminhoMysqldump,
							"--user=root", "--password=admin", "controle",
							"--result-file=" + caminhoArquivo);
					Process process = pb.start();
					while (process.isAlive()) {
					}
					controle.notifyAll();
				}
			} catch (IOException e) {
				// Provavelmente o arquivo não foi encontrado
				NegocioException ne = new NegocioException(
						"backup.verifique_se_o_diretorio_existe", e);
				tratarExcecaoBackup(ne);
			} catch (NegocioException e) {
				tratarExcecaoBackup(e);
			}

		}

	}

	/**
	 * Exibe a mensagem de erro e fecha a tela de backup
	 */
	private void tratarExcecaoBackup(NegocioException e) {
		this.controladorProgressBar.fecharPopup();
		tratarErro(e);
	}

	// Não utilizado
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
