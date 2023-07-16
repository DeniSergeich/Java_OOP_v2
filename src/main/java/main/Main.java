package main;

import hero.BaseHero;
import hero.*;
import view.AnsiColors;
import view.View;

import java.util.*;

public class Main {
    public static ArrayList<BaseHero> team1 = new ArrayList<>();
    public static ArrayList<BaseHero> team2 = new ArrayList<>();
    public static ArrayList<BaseHero> allTeam = new ArrayList<>();

    public static void main(String[] args) {
        fillList(team1, 1);
        fillList(team2, 10);
        allTeam.addAll(team1);
        allTeam.addAll(team2);
        allTeam.sort(Comparator.comparingInt(BaseHero::getInitiative));
        Scanner in = new Scanner(System.in);
        while (isAtLeastOneAlive(team1) && isAtLeastOneAlive(team2)){
            View.view();
            for (BaseHero person : allTeam) {
                if (team1.contains(person)) person.step(team2, team1);
                else person.step(team1, team2);
            }
            System.out.println("Нажмите ENTER");
            in.nextLine();
        }
        if(isAtLeastOneAlive(team1)) System.out.println(AnsiColors.ANSI_GREEN + "Победили зеленые!" + AnsiColors.ANSI_RESET);
        else System.out.println(AnsiColors.ANSI_BLUE + "Победили синие!" + AnsiColors.ANSI_RESET);
        View.view();

    }

    public static boolean isAtLeastOneAlive(ArrayList<BaseHero> team) {
        for (BaseHero person : team) {
            if (!person.isDead()) return true;
        }
        return false;
    }

    public static void fillList(ArrayList<BaseHero> list, int xPosition) {
        Names[] names = Names.values();
        for (int i = 1; i <= 10; i++) {
            int cnt = new Random().nextInt(7);
            int pos = new Random().nextInt(0,2);
            if (xPosition > 5) pos *= -1;
            Names name = names[new Random().nextInt(names.length)];
            switch (cnt) {
                case 0 -> list.add(new Peasant(name, xPosition+pos, i));
                case 1 -> list.add(new Magician(name, xPosition+pos, i));
                case 2 -> list.add(new Sniper(name, xPosition+pos, i));
                case 3 -> list.add(new Spearman(name, xPosition+pos, i));
                case 4 -> list.add(new Monk(name, xPosition+pos, i));
                case 5 -> list.add(new Crossbowman(name, xPosition+pos, i));
                case 6 -> list.add(new Bandit(name, xPosition+pos, i));
            }
        }
    }
}
