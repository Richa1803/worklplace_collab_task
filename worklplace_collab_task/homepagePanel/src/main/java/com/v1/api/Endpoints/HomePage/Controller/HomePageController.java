package com.v1.api.Endpoints.HomePage.Controller;

import com.v1.api.Endpoints.HomePage.Models.MainRequestModel;
import com.v1.api.Endpoints.HomePage.Models.ResponseModel;
import com.v1.api.Endpoints.HomePage.Services.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    private HomePageService service;

    @PostMapping(value = "/v1/api/homepage-panel/tableinfo")
    public List<String> GetTableInfo(@RequestBody MainRequestModel requestModel) throws SQLException{
       List<String> result = service.GetTableInfo(requestModel);
        return result;
    }

    @PostMapping(value = "/v1/api/homepage-panel/insertQuery")
    public ResponseModel InsertQuery(@RequestBody MainRequestModel requestModel)throws SQLException{
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(service.InsertIntoHomepageBanner(requestModel));
        return responseModel;
    }

    @PostMapping(value = "/v1/api/homepage-panel/updateQuery")
    public ResponseModel UpdateQuery(@RequestBody MainRequestModel requestModel)throws SQLException{
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(service.UpdateHomepageBanner(requestModel));
        return responseModel;
    }

}
