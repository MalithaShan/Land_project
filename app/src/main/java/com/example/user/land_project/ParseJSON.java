package com.example.user.land_project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJSON {
    public static String[] ids;
    public static String[] Shapes;
    public static String[] Colours;
    public static String[] Position_Xs;
    public static String[] Position_Ys;
    public static String[] Messages;
    private static Details Details;
    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public static final String KEY_Shape = "Shape";
    public static final String KEY_Colour = "Colour";
    public static final String KEY_Position_X = "Position_X";
    public static final String KEY_Position_Y = "Position_Y";
    public static final String KEY_Message = "Message";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    public static com.example.user.land_project.Details getDetails() {
        return Details;
    }

    public static void setDetails(com.example.user.land_project.Details details) {
        Details = details;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids= new String[users.length()];
            Shapes=new String[users.length()];
            Colours=new String[users.length()];
            Position_Xs=new String[users.length()];
            Position_Ys=new String[users.length()];
            Messages = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                Shapes[i] = jo.getString(KEY_Shape);
                Colours[i] = jo.getString(KEY_Colour);
                Position_Xs[i] = jo.getString(KEY_Position_X);
                Position_Ys[i] = jo.getString(KEY_Position_Y);
                Messages[i] = jo.getString(KEY_Message);
            }

            getDetails().id=ids[0];
            getDetails().Shape=Shapes[0];
            getDetails().Colour=Colours[0];
            getDetails().Position_X=Position_Xs[0];
            getDetails().Position_Y=Position_Ys[0];
            getDetails().Message=Messages[0];


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
