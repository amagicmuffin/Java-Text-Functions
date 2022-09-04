/** health bar? */
class Temp {
    public static String hpBar(double hp, double maxHp) {
        // ▓  ░ █
        final int BAR_LEN = 10;

        double percentHPLeft = hp/maxHp;

        int bars = (int) (percentHPLeft*10);

        String output = "";
        
        for(int i = 0; i < 10; i++) {
            if(i < bars) output +=  "▓";
            else output += "░";
        }

        return output;
    }
    
    public static void main(String[] args) {
        System.out.println(hpBar(2,4));
        System.out.println(hpBar(4,8));
        System.out.println(hpBar(2,5));
        System.out.println(hpBar(345,567));
    }
}