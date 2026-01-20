###Dashboard###

TelemetryPacket packet = new TelemetryPacket(); //for creating new packets
packet.put(String, Object); //Put data in the packet

packet.fieldOverlay() //get the field canvas
    .setFill("<COLOR_NAME>") //fill with color from: https://developer.mozilla.org/en-US/docs/Web/CSS/Guides/Colors/Applying_color#how_to_describe_a_color
    .fillRect(X1, Y1, X2, Y2);
