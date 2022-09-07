package com.example.sunlab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SUNLabRecordController implements Initializable {

    @FXML
    private TableView<SUNLabRecordModel> SUNLabTable;
    @FXML
    private TableColumn<SUNLabRecordModel,Integer> studentIDColumn;

    @FXML
    private TableColumn<SUNLabRecordModel,String> entryColumn;

    @FXML
    private TableColumn<SUNLabRecordModel,String> entryTimeColumn;

    @FXML
    private TableColumn<SUNLabRecordModel,String> exitColumn;

    @FXML
    private TableColumn<SUNLabRecordModel,String> exitTimeColumn;



    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField studentID;

    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;




    public String getdateandtime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String formattedDateTime = now.format(dtf);

        return formattedDateTime;
    }

    public void onBrowserClicked() throws SQLException {

        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectdb=connectNow.getconnected();

        if(studentID.getText().isBlank()==false)
        {
            if( startDate.getValue()==null)
            {
                if(endDate.getValue()==null)
                {
                    if(startTime.getText().isBlank()==true)
                    {
                        if (endTime.getText().isBlank() == true)
                         {
                             String query1="SELECT * FROM sun_lab_system.sun_lab_access WHERE StudentID='"+studentID.getText()+"';";
                             runandprint(query1);
                         }
                        else
                        {
                            //please enter start time
                        }
                    }
                    else {
                        if(endTime.getText().isBlank()==true)
                         {
                            //please enter end time
                         }
                        else {

                            String query2="SELECT * FROM sun_lab_system.sun_lab_access WHERE StudentID='"+studentID.getText()+"' AND insideTime<='"+endTime.getText()+"' AND insideTime>='"+startTime.getText()+"' ORDER BY insideDate DESC, insideTime DESC ";
                            runandprint(query2);
                         }

                    }


                }
                else {//please enter start date

                }


            }
            else {
                if(endDate.getValue()!=null)
                {
                    if(startTime.getText().isBlank()==true && endTime.getText().isBlank()==true)
                    {
                        String query3="SELECT * FROM sun_lab_system.sun_lab_access WHERE StudentID='"+studentID.getText()+"' AND insideDate<='"+endDate.getValue().toString()+"' AND insideDate>='"+startDate.getValue().toString()+"' ORDER BY insideDate DESC, insideTime DESC ";
                        runandprint(query3);
                    }
                    else if(startTime.getText().isBlank()==true || endTime.getText().isBlank()==true)
                    {
                        //please complete time
                    }
                    else if(startTime.getText().isBlank()==false && endTime.getText().isBlank()==false)
                    {
                        String query4="SELECT * FROM sun_lab_system.sun_lab_access WHERE StudentID='"+studentID.getText()+"' AND insideDate<='"+endDate.getValue().toString()+"' AND insideDate>='"+startDate.getValue().toString()+"' AND insideTime<='"+endTime.getText()+"' AND insideTime>='"+startTime.getText()+"' ORDER BY insideDate DESC, insideTime DESC ";
                        runandprint(query4);
                    }
                }




            }

        }
        else {

            if( startDate.getValue()==null)
            {
                if(endDate.getValue()==null)
                {
                    if(startTime.getText().isBlank()==true)
                    {
                        if (endTime.getText().isBlank() == true)
                        {
                            String query5="SELECT * FROM sun_lab_system.sun_lab_access  ORDER BY insideDate DESC, insideTime DESC ";
                            runandprint(query5);
                        }
                        else
                        {
                            //please enter start time
                        }
                    }
                    else {
                        if(endTime.getText().isBlank()==true)
                        {
                            //please enter end time
                        }
                        else {

                            String query6="SELECT * FROM sun_lab_system.sun_lab_access WHERE insideTime<='"+endTime.getText()+"' AND insideTime>='"+startTime.getText()+"' ORDER BY insideDate DESC, insideTime DESC";
                            runandprint(query6);
                        }

                    }


                }
                else {//please enter start date

                }


            }
            else {

                if(endDate.getValue()!=null)
                {
                    if(startTime.getText().isBlank()==true && endTime.getText().isBlank()==true)
                    {
                        String query3="SELECT * FROM sun_lab_system.sun_lab_access WHERE insideDate<='"+endDate.getValue().toString()+"' AND insideDate>='"+startDate.getValue().toString()+"' ORDER BY insideDate DESC, insideTime DESC ";
                        runandprint(query3);
                    }
                    else if(startTime.getText().isBlank()==true || endTime.getText().isBlank()==true)
                    {
                        //please complete time
                    }
                    else if(startTime.getText().isBlank()==false && endTime.getText().isBlank()==false)
                    {
                        String query4="SELECT * FROM sun_lab_system.sun_lab_access WHERE insideDate<='"+endDate.getValue().toString()+"' AND insideDate>='"+startDate.getValue().toString()+"' AND insideTime<='"+endTime.getText()+"' AND insideTime>='"+startTime.getText()+"' ORDER BY insideDate DESC, insideTime DESC ";
                        runandprint(query4);
                    }
                }




            }

        }
    }
    public void runandprint(String query) throws SQLException {
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectdb=connectNow.getconnected();
        ObservableList<SUNLabRecordModel> SUNLabRecordsObservableList= FXCollections.observableArrayList();
        Statement statement=connectdb.createStatement();

        ResultSet result= statement.executeQuery(query);



        while (result.next())
        {
            Integer a=result.getInt("StudentID");
            String b=result.getString("insideDate");
            String c=result.getString("outsideDate");
            String d=result.getString("insideTime");
            String e=result.getString("outsideTime");

            SUNLabRecordsObservableList.add( new SUNLabRecordModel(a,b,c,d,e));
        }
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        entryColumn.setCellValueFactory(new PropertyValueFactory<>("insideDate"));
        exitColumn.setCellValueFactory(new PropertyValueFactory<>("outsideDate"));
        entryTimeColumn.setCellValueFactory(new PropertyValueFactory<>("insideTime"));
        exitTimeColumn.setCellValueFactory(new PropertyValueFactory<>("outsideTime"));

        SUNLabTable.setItems(SUNLabRecordsObservableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        String query="SELECT * FROM sun_lab_system.sun_lab_access ORDER BY insideDate DESC, insideTime DESC;";
        try {
            runandprint(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}