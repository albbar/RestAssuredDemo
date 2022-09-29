package hooks;

import io.restassured.response.Response;
import others.Helpers;
import stepsDefinitions.utils.APIResource;
import stepsDefinitions.utils.DataDrivenExcel;
import stepsDefinitions.utils.Excel;
import stepsDefinitions.utils.TestDataBuild;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepsDefinitions.utils.Constants.*;

public class GoogleMapApiHook extends Helpers {


    public String AddPlace() throws IOException{

        Excel excel = new Excel(DATA_EXCEL_PATH, "DeletePlace", true, 1);
        DataDrivenExcel dataDrivenExcel = new DataDrivenExcel();
        Map<String, String> data = new HashMap<>();
        data = dataDrivenExcel.readExcel(excel);
        TestDataBuild dataBuild = new TestDataBuild();
        APIResource resourceAPI = APIResource.valueOf(ADD_PLACE_API);
        Response response;


        response = given()
                .spec(requestSpecificationGoogleMapAPI(GOOGLE_MAP_API))
                .body(dataBuild.addPlacePayLoad(data.get(ACCURACY), data.get(NAME), data.get(LANGUAGE),
                        data.get(ADDRESS), data.get(LAT_LOCATION), data.get(LNG_LOCATION), data.get(PHONE_NUMBER), data.get(WEB_SITE)))
                .when().post(resourceAPI.getResource());

        String placeID = getJsonPath(response, PLACE_ID);

        return placeID;
    }
}
