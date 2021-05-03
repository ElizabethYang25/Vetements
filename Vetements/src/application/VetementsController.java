package application;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VetementsController implements Initializable{

    @FXML
    private TextField txtPrix;

    @FXML
    private TableColumn<Vetements, String> prixColumn;

    @FXML
    private ComboBox<String> cboType;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TableColumn<Vetements, String> typeColumn;

    @FXML
    private Button btnClear;

    @FXML
    private TableView<Vetements> vetementsTable;

    @FXML
    private Button btnEffacer;

    @FXML
    private TableColumn<Vetements, String> quantityColumn;

    @FXML
    private Button btnModifier;

    @FXML
    private TableColumn<Vetements, String> nomColumn;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtNom;



    //liste des types de vetements
    private ObservableList<String> listT=(ObservableList<String>) FXCollections.observableArrayList("Chemise", "Pantalon", "Veste", "Jupe", "Chandail"); 

    //Placer les vetements dans une observable list
    public ObservableList<Vetements> vetementsData=FXCollections.observableArrayList();

    //Créer une méthode pour accéder à la liste des vetements

    public ObservableList<Vetements> getetudiantData()
    {
    	return vetementsData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
	{
		cboType.setItems(listT);
		//attribuer les valeurs aux colonnes du tableView
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
		prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
		vetementsTable.setItems(vetementsData);
		
		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnClear.setDisable(true);
		
		showVetements(null);
		//Mettre à jour l'affichage d'un étudiant sélectrionné
			vetementsTable.getSelectionModel().selectedItemProperty().addListener((
						observable, oldValue, newValue)-> showVetements(newValue));
		
	}

    private void showVetements(Object object) {
		
	}

	@FXML
	public void verifNum() //methode pour trouver des input non numériques
	{
		txtPrix.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
		
		{
			txtPrix.setText(newValue.replaceAll("[^\\d*\\.]","")); //si le input est non numérique, ca le remplace
		}
		}); 
		
	}
	
	@FXML
	public void verifNum1() //methode pour trouver des input non numériques
	{
		txtQuantity.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
		
		{
			txtQuantity.setText(newValue.replaceAll("[^\\d*\\.]","")); //si le input est non numérique, ca le remplace
		}
		}); 
		
	}
	
	//Ajouter des vetements
	@FXML
	void ajouter()
	{
		//vérifier si un champ n'est pas vide
		if(noEmptyInput())
		{
			Vetements tmp=new Vetements();

			tmp=new Vetements();
			tmp.setNom(txtNom.getText());
			tmp.setPrix(Double.parseDouble(txtPrix.getText()));
			tmp.setQuantity(Double.parseDouble(txtQuantity.getText()));
			tmp.setType(cboType.getValue());
			vetementsData.add(tmp);
			clearFields();
		}

	}



	//Effacer le contenu des champs
	@FXML
	void clearFields()
	{
		cboType.setValue(null);
		txtNom.setText("");
		txtQuantity.setText("");
		txtPrix.setText("");

	}
	
	//Afficher les vetements
			public void showVetements(Vetements etudiant)
			{
				if(etudiant !=null)
				{
					
					Vetements vetements = null;
					cboType.setValue(vetements.getType());
					txtNom.setText(etudiant.getNom());
					txtPrix.setText(Double.toString(vetements.getPrix()));
					txtQuantity.setText(Double.toString(vetements.getQuantity()));
					btnModifier.setDisable(false);
					btnEffacer.setDisable(false);
					btnClear.setDisable(false);
					
				}
				else
				{
					clearFields();
				}
				
				
			}

			//mise à jour d'un vetement
			@FXML
			public void updateVetements()
			{
				//vérifier si un champ n'est pas vide
				if(noEmptyInput())
				{
					Vetements etudiant=vetementsTable.getSelectionModel().getSelectedItem();

					Vetements vetements = null;
					vetements.setNom(txtNom.getText());
					vetements.setType(cboType.getValue());
					vetements.setPrix(Double.parseDouble(txtPrix.getText()));
					vetements.setQuantity(Double.parseDouble(txtQuantity.getText()));
					vetementsTable.refresh();
				}

			}
			//effacer un vetement
			@FXML
			public void deleteVetements()
			{
				int selectedIndex = vetementsTable.getSelectionModel().getSelectedIndex();
				if (selectedIndex >=0)
				{
					Alert alert=new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Effaver");
					alert.setContentText("confirmer la suppression!");
					Optional<ButtonType> result=alert.showAndWait();
					if(result.get()==ButtonType.OK)
						vetementsTable.getItems().remove(selectedIndex);
				}
			}
	    

			//vérifier champs vides
			private boolean noEmptyInput()
			{
				String errorMessage="";
				if(txtNom.getText().trim().equals(""))
				{
					errorMessage+="Le champ nom ne doit pas être vide! \n";
				}
				if(txtQuantity.getText()==null||txtQuantity.getText().length()==0)
				{
					errorMessage+="Le champ quantité ne doit pas être vide! \n";
				}
				if(txtPrix.getText()==null||txtPrix.getText().length()==0)
				{
					errorMessage+="Le champ prix ne doit pas être vide! \n";
				}
				if(cboType.getValue()==null)
				{
					errorMessage+="Lechamp type ne doit pas être vide! \n";
				}
				
				if(errorMessage.length()==0)
				{
					return true;
				}
				else
				{
					Alert alert=new Alert(AlertType.ERROR);
					alert.setTitle("Champs manquants");
					alert.setHeaderText("Completer les champs manquants");
					alert.setContentText(errorMessage);
					alert.showAndWait();
					return false;
				}
			}
			
			
		    //SAUVEGARDE DE DONNÉES
			
				//Recupérer le chemin des fichiers si ca existe
			public File getVetementsFilePath()
			{
				Preferences prefs = Preferences.userNodeForPackage(Main.class);
						String filePath = prefs.get("filePath", null);
						
						if (filePath != null)
						{
							return new File(filePath);
						}
						else
						{
							return null;
						}
						
			}


			//Attribuer un chemin de fichiers
			
			public void setVetementsFilePath(File file)
			{
				Preferences prefs = Preferences.userNodeForPackage(Main.class);
				if (file != null)
				{
					prefs.put("filePath", file.getPath());
				}
				else
				{
					prefs.remove("filePath");
				}
			}
		    
			//Prendre les données de type XML et les convertir en données de type javafx
			public void loadVetementsDataFromFile(File file)
			{
				try {
					JAXBContext context = JAXBContext.newInstance(VetementListWrapper.class);
					Unmarshaller um = context.createUnmarshaller();
					
					VetementListWrapper wrapper = (VetementListWrapper) um.unmarshal(file);
					vetementsData.clear();
					vetementsData.addAll(wrapper.getEtudiants());
					setVetementsFilePath(file);
					
					//donner le titre du fichier chargé
					Stage pStage=(Stage) vetementsTable.getScene().getWindow();
					pStage.setTitle(file.getName());
					
				} catch(Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("les données n'ont pas été trouvées");
					alert.setContentText("Les données ne pouvaient pas étre trouvées dans le fichier : \n" +file.getPath());
					alert.showAndWait();
					
				
				}
			}
				
			//Prendre les données de type JavaFx et els convertir en type XML
			public void saveVetementsDataToFile(File file) {
				try {
					JAXBContext context = JAXBContext.newInstance(VetementListWrapper.class);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					VetementListWrapper wrapper = new VetementListWrapper();
					wrapper.setEtudiants(vetementsData);
					
					m.marshal(wrapper, file);
					
					//sauvegarder dans le registre
					setVetementsFilePath(file);
					
					//donner le titre du fichier sauvegardé
					Stage pStage=(Stage) vetementsTable.getScene().getWindow();
					pStage.setTitle(file.getName());
					
				} catch (Exception e) {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("Données non sauvegardées");
					alert.setContentText("Les données ne pouvaient pas être sauvegardées dans le fichier:\n" + file.getPath());
					alert.showAndWait();
					
				}
			}
			
			//commencer un nouveau
			@FXML
			private void handleNew()
			{
				((List<String>) getVetementsFilePath()).clear();
				setVetementsFilePath(null);
			}
			
			/*
			 * Le FileChooser permet à l'usager de choisir le fichier à ouvrir.
			 */
			@FXML
			private void handleOpen() {
				FileChooser fileChooser = new FileChooser();
				
				//permettre un filtre sur l'extension du fichier à chercher
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
						"XML file (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				
				//montrer le dialogue
				File file = fileChooser.showOpenDialog(null);
				
				if(file != null) {
					loadVetementsDataFromFile(file);
				}
				
			}
			
			/*
			 * Sauvegarde le fichier correspondant à le vetement actif
			 * S'il n y a pas de fichier, le menu sauvegarder sous va s'afficher
			 */
			@FXML
			private void handleSave() {
				
				File etudiantFile = getVetementsFilePath();
				if (etudiantFile != null) {
					saveVetementsDataToFile(etudiantFile);
					
				} else {
					handleSaveAs();
				}
			}
			
			/*
			 * Ouvrir le FileChooser pour trouver le chemin
			 */
			@FXML
			private void handleSaveAs() {
				FileChooser fileChooser = new FileChooser();
				
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
						"XML file (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				
				//sauvegarde
				File file = fileChooser.showSaveDialog(null);
				
				if (file != null) {
					//vérification de l'extension
					if (!file.getPath().endsWith(".xml")) {
						file = new File(file.getPath() + ".xml");
					}
					saveVetementsDataToFile(file);
				}
				
			}
			
		}

