class Main {
    public static void main(String[] args) {
        Text.useANSI = true;

        Text.cls();
        System.out.println("3");
        Text.wait(500);
        Text.cls();
        System.out.println("2");
        Text.wait(500);
        Text.cls();
        System.out.println("1");
        Text.wait(500);
        System.out.println();
        
        Text.slowPrint(" A broken ", "red");
        Text.slowPrint("ring ", "white");
        Text.slowPrint("of mail.\n", "red");

        for(String[] colorDict : Text.ANSI_COLORS) {
            Text.slowPrint("██", colorDict[0]);
        }
        System.out.println();
    }
}