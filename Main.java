class Main {
    public static void main(String[] args) {
        Text.useANSI = true;

        Text.print("§redred §grayand §whitewhite §grayand §blueblue\n");
        Text.wait(500);
        Text.slowPrint("§redred §grayand §whitewhite §grayand §blueblue\n");
        
        for(String[] colorDict : Text.ANSI_COLORS) {
            Text.slowPrint("██", colorDict[0]);
        }
        Text.print("\n");
        Text.wait(1500);

        Text.cls();
        Text.print("3\n");
        Text.wait(500);
        Text.cls();
        Text.print("2\n");
        Text.wait(500);
        Text.cls();
        Text.print("1\n");
        Text.wait(500);

        Text.slowPrint(" A broken ", "red");
        Text.slowPrint("ring ", "white");
        Text.slowPrint("of mail.\n", "red");

    }
}