package Currency.UI;

import Currency.Logic.Admin;
import Currency.Logic.CurrencyConverter;
import Currency.Logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static String filepath = "src/main/java/Currency/Logic/RateTxt.txt";;
    public CurrencyConverter cc = new CurrencyConverter(filepath);
    public Admin admin = new Admin(cc);
    public User user = new User(cc);

    @FXML
    public TableView<Rate> RatesTable;
    public TableColumn<Rate, String> RateColumn;
    public TableColumn<Rate, String> DateColumn;
    public Pane LoginPage;
    public Pane AdminPage;
    public Pane UserPage;

    @FXML
    public Pane pane_Trending,
            pane_Query, pane_Currency, pane_Convert, pane_AddNew,
            pane_ManageExisted_Trending, pane_ManageExisted_Rate;
    public GridPane TrendingTable;

    public Pane pane_Convert_User, pane_Trending_User, pane_Query_User;
    public GridPane TrendingTableUser;
    public ChoiceBox ChoiceBox1;
    public ChoiceBox ChoiceBox2;
    public TextField FromTextBoxInConvert;
    public TextField ToTextBoxInConvert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<String> list = FXCollections.observableArrayList(cc.getAllCurrency());
        ChoiceBox1.setItems(list);
        ChoiceBox2.setItems(list);
    }

    private void vision_control(Pane toVisiable){
        pane_Trending.setVisible(false);
        pane_Query.setVisible(false);
        pane_Currency.setVisible(false);
        pane_Convert.setVisible(false);
        pane_AddNew.setVisible(false);
        pane_ManageExisted_Trending.setVisible(false);
        pane_ManageExisted_Rate.setVisible(false);
        toVisiable.setVisible(true);
        toVisiable.toFront();
    }

    public void ConvertButtonClick(ActionEvent actionEvent) {
        vision_control(pane_Convert);
    }

    public void TrendingButtonClick(ActionEvent actionEvent) {
        vision_control(pane_Trending);
    }

    public void QueryButtonClick(ActionEvent actionEvent) {
        vision_control(pane_Query);
    }

    public void CurrencyButtonClick(ActionEvent actionEvent) {
        vision_control(pane_Currency);
    }

    public void Update(ActionEvent actionEvent) {
        RatesTable.getItems().add(new Rate("1111", "1111"));
    }

    public void ToAddNewPage(ActionEvent actionEvent) {
        vision_control(pane_AddNew);
    }

    public void ToTrending(ActionEvent actionEvent) {
        vision_control(pane_ManageExisted_Trending);
    }

    public void ToCurrencyPage(ActionEvent actionEvent) {
        vision_control(pane_Currency);
    }

    public void ToManageRate(ActionEvent actionEvent) {
        vision_control(pane_ManageExisted_Rate);
    }

    public void test(ActionEvent actionEvent) {
        Label L1 = (Label) TrendingTable.getChildren().get(19);
        L1.setText("PPP");
    }

    public void ToAdminPage(ActionEvent actionEvent) {
        AdminPage.setVisible(true);
        AdminPage.toFront();
        LoginPage.setVisible(false);
        UserPage.setVisible(false);
    }

    public void ToLoginPage(ActionEvent actionEvent) {
        LoginPage.setVisible(true);
        LoginPage.toFront();
        AdminPage.setVisible(false);
        UserPage.setVisible(false);
    }

    public void ToUserPage(ActionEvent actionEvent) {
        UserPage.setVisible(true);
        UserPage.toFront();
        AdminPage.setVisible(false);
        LoginPage.setVisible(false);
    }

    public void ConvertButtonClickUser(ActionEvent actionEvent) {
        pane_Convert_User.setVisible(true);
        pane_Convert_User.toFront();
        pane_Trending_User.setVisible(false);
        pane_Query_User.setVisible(false);
    }

    public void TrendingButtonClickUser(ActionEvent actionEvent) {
        pane_Trending_User.setVisible(true);
        pane_Trending_User.toFront();
        pane_Convert_User.setVisible(false);
        pane_Query_User.setVisible(false);
    }

    public void QueryButtonClickUser(ActionEvent actionEvent) {
        pane_Query_User.setVisible(true);
        pane_Query_User.toFront();
        pane_Convert_User.setVisible(false);
        pane_Trending_User.setVisible(false);
    }

    public void ConfirmInConvert(ActionEvent actionEvent) {
        String currencyA = (String)ChoiceBox1.getValue();
        String currencyB = (String)ChoiceBox2.getValue();

        double fromValue = Double.parseDouble(FromTextBoxInConvert.getText());
        String toValue = Double.toString(fromValue*cc.getRate(currencyA, currencyB));
        ToTextBoxInConvert.setText(toValue);
    }
}
