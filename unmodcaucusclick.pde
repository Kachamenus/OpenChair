public void unmodcaucusclick(GButton source, GEvent event) { //_CODE_:unmodcaucus:576276:
  clearscreen();
  background(backgroundcolour);
  mode = new GLabel(this, width / 2, 50, 500, 200);
  mode.setFont(new Font("Arial", Font.PLAIN, 40));
  mode.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  mode.setText("");
  mode.setText("Unoderated Caucus");
  modetype = "unmodcaucus";
  println("unmodcaucus - GButton >> GEvent." + event + " @ " + millis());
      textSize(24);
      mode.setText("Unmoderated Caucus");
      textSize(12);
  loadingbar = loadImage("images/loadingbar.png");
  loadingbarcaucus = loadImage("images/loadingbarinside.png");
  image(loadingbar, width / 4, 2 * height / 3 + loadingbar.height, width / 2, loadingbar.height);
  start = new GButton(this, (width / 2 - width / 14) - width/7, 6 * height / 7, width / 7, 40);
  start.setText("Start");
  start.addEventHandler(this, "startclick");
  start.setVisible(true);
  reset = new GButton(this, width / 2 - width/14, 6 * height / 7, width / 7, 40);
  reset.setText("Reset");
  reset.addEventHandler(this, "resetclick");
  reset.setVisible(true);
  pause = new GButton(this, (width / 2 -width/14) +  width/7, 6 * height / 7, width / 7, 40);
  pause.setText("Pause");
  pause.addEventHandler(this, "pauseclick");
  pause.setVisible(true);
} //_CODE_:unmodcaucus:576276:   
