package com.projetos.controle.tela.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
import org.springframework.stereotype.Controller;

import com.projetos.controle_negocio.service.base.ParametroService;
import com.projetos.controle_util.conversao.DateUtil;

@SuppressWarnings("deprecation")
@Controller
/**
 * Controller da tela de backup
 * @author Rafael
 */
public class BackupController {

	@Autowired
	private ParametroService parametroService;

	/**
	 * Objeto usado para controlar as threads envolvendo a geração do arquivo e
	 * a barra de progresso
	 */
	private Object controle = new Object();

	private String caminhoArquivo;

	/**
	 * Monta a tela de backup e exibe
	 * 
	 * @param primaryStage
	 */
	public void exibirTelaBackup(Stage primaryStage) {

		Label statusLabel = new Label("");
		ProgressBar progressBar = new ProgressBar();
		Button runButton = new Button("Gerar Backup");
		progressBar.setPrefWidth(320);
		progressBar.setVisible(true);
		progressBar.setProgress(0);

		final VBox layout = VBoxBuilder.create().spacing(8)
				.children(VBoxBuilder.create().spacing(5).children(HBoxBuilder.create().spacing(10).children(runButton, statusLabel).build(), progressBar).build()).build();
		layout.setStyle("-fx-background-color: white; -fx-padding:20; -fx-font-size: 16;");

		Stage popup = new Stage();
		popup.initOwner(primaryStage);
		popup.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(layout);
		popup.setScene(scene);
		popup.show();

		runButton.setOnAction(new ControladorProgressBar(runButton, statusLabel, progressBar, popup));
	}

	private class ControladorProgressBar implements EventHandler<ActionEvent> {

		private Label statusLabel = new Label("Status");
		private ProgressBar progressBar = new ProgressBar();
		private Button runButton;
		private Stage popup;

		public ControladorProgressBar(Button runButton, Label statusLabel, ProgressBar progressBar, Stage popup) {
			this.runButton = runButton;
			this.statusLabel = statusLabel;
			this.progressBar = progressBar;
			this.popup = popup;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void handle(ActionEvent actionEvent) {
			@SuppressWarnings("rawtypes")
			final Task task = new Task() {

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
			};

			statusLabel.textProperty().bind(task.messageProperty());
			runButton.disableProperty().bind(task.runningProperty());
			progressBar.progressProperty().bind(task.progressProperty());
			task.stateProperty().addListener(new ChangeListener<Worker.State>() {
				@Override
				public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
					if (newState == Worker.State.SUCCEEDED) {
						runButton.setText("Salvar Arquivo");
						runButton.setOnAction(new ControladorFileChooser(popup));
					}
				}
			});

			new Thread(task).start();
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
				throw new BackupException(e);
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
					// Gera o arquivo na pasta configurada (Provavelmente
					// C:\controle\backup)
					caminhoArquivo = parametroService.getCaminhoBackupArquivos().getValor();
					String date = DateUtil.fullDateTime();
					caminhoArquivo = caminhoArquivo.concat("\\backup").concat(date).concat(".sql");

					String caminhoMysqldump = parametroService.getCaminhoMysqldump().getValor();
					ProcessBuilder pb = new ProcessBuilder(caminhoMysqldump, "--user=root", "--password=admin", "controle", "--result-file=" + caminhoArquivo);
					Process process = pb.start();
					while (process.isAlive()) {
						System.out.println(process.isAlive());
					}
					controle.notifyAll();
				}
			} catch (IOException e) {
				throw new BackupException(e);
			}
		}

	}

	/**
	 * Exceção do processo de backup
	 * 
	 * @author Rafael
	 */
	private class BackupException extends RuntimeException {

		public BackupException(Throwable cause) {
			super(cause);
		}

		private static final long serialVersionUID = 0L;

	}

}
