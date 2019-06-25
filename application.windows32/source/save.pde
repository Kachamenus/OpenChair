public void saveclick(GButton source, GEvent event) { //_CODE_:notes:768397:
  println("saving...");
  
  for(int i = 0; i < positionarray.size(); i++){
    JSONObject json;

    String position = positionarray.get(i).getPosition();
    json = new JSONObject();
    json.setInt("id", positionarray.get(i).getId());
    json.setString("position", positionarray.get(i).getPosition());
    json.setInt("speaktime", positionarray.get(i).getSpeaktime());
    json.setInt("speeches", positionarray.get(i).getSpeeches());
    json.setString("chairnotes", positionarray.get(i).getChairnotes());
    json.setBoolean("attendance", positionarray.get(i).getAttendance());
  
    String jpeg = (positionarray.get(i).getImageurl());
    json.setString("imageurl", jpeg);

    String savefile = ("data/positions/" + position + ".json");
    saveJSONObject(json, savefile);
  }
  json = new JSONObject();
  json.setInt("totaltimespoken", totaltimespoken);
  json.setInt("totalspeeches", totalspeeches);
  String savefile = ("data/stats.json");
  saveJSONObject(json, savefile);
  println("saved");
} //_CODE_:notes:768397:

public void save(){
  println("saving...");
  
  for(int i = 0; i < positionarray.size(); i++){
    String position = positionarray.get(i).getPosition();
    json = new JSONObject();
    json.setInt("id", positionarray.get(i).getId());
    json.setString("position", positionarray.get(i).getPosition());
    json.setInt("speaktime", positionarray.get(i).getSpeaktime());
    json.setInt("speeches", positionarray.get(i).getSpeeches());
    json.setString("chairnotes", positionarray.get(i).getChairnotes());
    json.setBoolean("attendance", positionarray.get(i).getAttendance());
  
    String jpeg = (positionarray.get(i).getImageurl());
    json.setString("imageurl", jpeg);

    String savefile = ("data/positions/" + position + ".json");
    saveJSONObject(json, savefile);
  }
  json = new JSONObject();
  json.setInt("totaltimespoken", totaltimespoken);
  json.setInt("totalspeeches", totalspeeches);
  String savefile = ("data/stats.json");
  saveJSONObject(json, savefile);
  println("saved");
}
