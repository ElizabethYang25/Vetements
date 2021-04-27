package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VetementsController {

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
    private ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("Chemise", "Pantalon", "Veste", "Jupe", "Chandail"); 

    //Placer les vetements dans une observable list
    public ObservableList<Vetements> etudiantData=FXCollections.observableArrayList();

    //Créer une méthode pour accéder à la liste des vetements

    public ObservableList<Vetements> getetudiantData()
    {
    	return etudiantData;
    }

    public void initialize(URL location, ResourceBundle resources) 
	{
		cboType.setItems(list);
		//attribuer les valeurs aux colonnes du tableView
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantité"));
		prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
		vetementsTable.setItems(etudiantData);
		
		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnClear.setDisable(true);
		
		showVetements(null);
		//Mettre à jour l'affichage d'un étudiant sélectrionné
			vetementsTable.getSelectionModel().selectedItemProperty().addListener((
						observable, oldValue, newValue)-> showVetements(newValue));
		
	}

    private void showVetements(Object object) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void verifNum () //methode pour trouver des input non numériques
	{
		txtQuantity.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
		
		{
			txtQuantity.setText(newValue.replaceAll("[^\\d*\\.]","")); //si le input est non numérique, ca le remplace
		}
		}); 
		
	}
	








