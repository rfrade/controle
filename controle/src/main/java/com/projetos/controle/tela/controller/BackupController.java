package com.projetos.controle.tela.controller;

import java.io.File;

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

import org.springframework.stereotype.Controller;

@SuppressWarnings("deprecation")
@Controller
public class BackupController {

	public void exibirTelaBackup(Stage primaryStage) {
		Label statusLabel = new Label("");
		ProgressBar progressBar = new ProgressBar();
		Button runButton = new Button("Gerar Backup");
		progressBar.setPrefWidth(320);
		progressBar.setVisible(false);

		final VBox layout = VBoxBuilder.create().spacing(8)
				.children(VBoxBuilder.create().spacing(5).children(HBoxBuilder.create().spacing(10)
						.children(runButton, statusLabel).build(), progressBar).build()).build();
		layout.setStyle("-fx-background-color: white; -fx-padding:20; -fx-font-size: 16;");

		Stage popup = new Stage();
		popup.initOwner(primaryStage);
		popup.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(layout);
		popup.setScene(scene);
		popup.show();

		runButton.setOnAction(new StartBackupButton(runButton, statusLabel, progressBar, popup));
	}

	class Backup implements EventHandler<ActionEvent> {

		private Stage popup;
		

		public Backup(Stage popup) {
			this.popup = popup;
		}

		public void runBackup() {

//		    stage.setScene(scene);
//		    stage.show();
			
			/*String novonome = null;
			int numerodobackup = 0;
			String arquivo = null;

			JFileChooser chooser = new JFileChooser("C:");
			chooser.setLocale(new Locale("ptBR"));
			chooser.setVisible(true);
			File file = new File("C:\\asdf.sql");
			chooser.setSelectedFile(file);

			int retorno = chooser.showSaveDialog(null);

			if (retorno == JFileChooser.APPROVE_OPTION) {
				try {
					if (!bck.isFile()) {
						String comando = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump.exe";
						ProcessBuilder pb = new ProcessBuilder(comando, "--user=root", "--password=admin", "controle", "--result-file=" + arquivo);
						pb.start();
						JOptionPane.showMessageDialog(null, "Cópia de segurança realizada com sucesso", "Backup", JOptionPane.CLOSED_OPTION);
					} else {
						while (bck.isFile()) {
							numerodobackup++;
							bck = new File(arquivo + numerodobackup);
							novonome = String.valueOf(bck);
						}
						String comando = "C:\\Program Files\\MySQL\\MySQL Server 5.0\\bin\\mysqldump.exe";
						ProcessBuilder pb = new ProcessBuilder(comando, "--user=root", "--password=1234", "Empresa", "--result-file=" + novonome);
						pb.start();
						JOptionPane.showMessageDialog(null, "Cópia de segurança realizada com sucesso!", "Backup", JOptionPane.CLOSED_OPTION);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e, "Cópia de Segurança não pode ser realizada!", 2);
				}

			}*/
		}

		@Override
		public void handle(ActionEvent event) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("/"));
			fileChooser.setInitialFileName("teste.sql");
			File diretorio = fileChooser.showSaveDialog(popup);
            if (diretorio != null) {
                try {
                	System.out.println(diretorio.getPath());
                	popup.close();
                    // salvar arquivo!
                } catch (/*IO*/Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
		}
	}

	class StartBackupButton implements EventHandler<ActionEvent> {

		private Label statusLabel = new Label("Status");
		private ProgressBar progressBar = new ProgressBar();
		private Button runButton;
		private Stage popup;

		public StartBackupButton(Button runButton, Label statusLabel, ProgressBar progressBar, Stage popup) {
			this.runButton = runButton;
			this.statusLabel = statusLabel;
			this.progressBar = progressBar;
			this.popup = popup;
		}

		@Override
		public void handle(ActionEvent actionEvent) {
			final Task task = new Task() {

				@Override
				protected ObservableList<String> call() throws InterruptedException {
//					progressBar.setVisible();
					updateMessage("Gerando Backup . . .");
					for (int i = 0; i < 10; i++) {
						Thread.sleep(200);
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
						runButton.setOnAction(new Backup(popup));
					}
				}
			});

			new Thread(task).start();
		}
	}

}
