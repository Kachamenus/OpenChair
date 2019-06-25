public void setmaxspeakchange(GTextField textfield, GEvent event) { //_CODE_:textarea1:333925:
  println("textfield1 - GTextArea >> GEvent." + event + " @ " + millis());
  if(isStringInt(setmaxspeak.getText())){
    spokentimermax = Integer.parseInt(setmaxspeak.getText());
  }
} //_CODE_:textarea1:333925:

public void setmaxcaucuschange(GTextField source, GEvent event) { //_CODE_:textfield1:427748:
  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
  if(isStringInt(setmaxcaucus.getText())){
    caucustimermax = Integer.parseInt(setmaxcaucus.getText());
  }
  resetclick(reset, event);
  image(loadingbarcaucus, width / 4, 2 * height / 3 + loadingbar.height);
} //_CODE_:textfield1:427748:

public boolean isStringInt(String s){
    try{
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException ex){
        return false;
    }
}
