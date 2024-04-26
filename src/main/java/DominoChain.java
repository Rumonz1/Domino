import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DominoChain {
    public int x;
    public int y;

    String sign;

    public DominoChain(int x, int y) {
        if (x < 0 || x > 6) {
            throw new IllegalArgumentException("Значение стороны костяшки должно быть от 0 до 6");
        }
            this.x = x;
            this.y = y;
            sign = "+";
    }


    public static boolean canConnect(ArrayList<Domino> chainOfDomino, Domino domino) {
        if (chainOfDomino.isEmpty()) {
            return true;
        }
        Domino lastDomino = chainOfDomino.get(chainOfDomino.size() - 1);
        return lastDomino.getY() == domino.getX();
    }

    public static ArrayList<Domino> findResult(ArrayList<Domino> dominoArrayList, ArrayList<Domino> chainOfDomino) {
        if (chainOfDomino.size() == dominoArrayList.size()) {
            return chainOfDomino;
        }
        for (Domino domino : dominoArrayList) {
            if (!chainOfDomino.contains(domino)) {
                if (canConnect(chainOfDomino, domino)) {
                    chainOfDomino.add(domino);
                    ArrayList<Domino> result = findResult(dominoArrayList, chainOfDomino);
                    if (result != null) {
                        return result;
                    }
                    chainOfDomino.remove(chainOfDomino.size() - 1);
                }
                domino.swapXY();
                if (canConnect(chainOfDomino, domino)) {
                    chainOfDomino.add(domino);
                    ArrayList<Domino> result = findResult(dominoArrayList, chainOfDomino);
                    if (result != null) {
                        return result;
                    }
                    chainOfDomino.remove(chainOfDomino.size() - 1);
                }
                domino.swapXY();
            }
        }
        return null;
    }
    public static void main(String[] args) {
//        Domino domino1 = new Domino(2, 6);
//        Domino domino2 = new Domino(3, 2);
//        Domino domino3 = new Domino(6, 6);
//        Domino domino4 = new Domino(3, 5);
//        Domino domino5 = new Domino(1, 5);
//        ArrayList<Domino> dominoArrayList1 = new ArrayList<>();
//        dominoArrayList1.add(domino1);
//        dominoArrayList1.add(domino2);
//        dominoArrayList1.add(domino3);
//        dominoArrayList1.add(domino4);
//        dominoArrayList1.add(domino5);
//        ArrayList<Domino> dominoArrayList2 = new ArrayList<>();
//        dominoArrayList2.add(domino1);
//        dominoArrayList2.add(domino2);
//        dominoArrayList2.add(domino3);
//        dominoArrayList2.add(domino4);
//        dominoArrayList2.add(domino5);

        Domino domino1 = new Domino(2, 2);
        Domino domino2 = new Domino(2, 2);
        Domino domino3 = new Domino(1, 6);
        Domino domino4 = new Domino(3, 2);
        Domino domino5 = new Domino(1, 2);
        ArrayList<Domino> dominoArrayList1 = new ArrayList<>();
        dominoArrayList1.add(domino1);
        dominoArrayList1.add(domino2);
        dominoArrayList1.add(domino3);
        dominoArrayList1.add(domino4);
        dominoArrayList1.add(domino5);



        ArrayList<Domino> result = findResult(dominoArrayList1, new ArrayList<>());
        if (result == null) {
            System.out.println("Решения нет");
        } else {
            for (Domino domino : result) {
                System.out.println(domino.getX() +"|"+ domino.getY() +" "+domino.getSign() );
            }
        }
    }
}
