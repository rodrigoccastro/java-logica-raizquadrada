package br.rodrigo;

public class Main {

    public static void main(String[] args) {

        try {

           for (int i=1; i<=100;i++)
                System.out.println("raiz("+i+"):" + raizQuadrada(i));

            for (double i=0.1; i<=0.9;i=i+0.1)
                System.out.println("raiz("+i+"):" + raizQuadrada(i));

        } catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
        }
    }

    private static double raizQuadrada(double valor) throws Exception {

        if (valor <= 0)
            throw new Exception();

        if (valor == 1)
            return 1;

        boolean achou = false;
        double tempMaior = valor;
        if ((tempMaior>0) && (tempMaior<1))
            tempMaior = tempMaior * 10;

        double tempMenor = valor / 2;
        double maiorMenor = 0;
        double antigoTempMenor = tempMenor;

        int count = 0;
        while (!achou) {
            count++;
            //System.out.println("maior:"+tempMaior+";menor:"+tempMenor+";mmenor:"+maiorMenor+";count:"+count);

            double testeMenor = tempMenor * tempMenor;
            if (testeMenor == valor) {
                achou = true;
            }
            double testeMaior = tempMaior * tempMaior;
            if (testeMaior == valor) {
                achou = true;
            }

            if (!achou) {
                if (testeMenor > valor) {
                    tempMaior = tempMenor;
                    antigoTempMenor = tempMenor;
                    tempMenor = tempMenor / 2;
                    if (tempMenor < maiorMenor) {
                        tempMenor = maiorMenor;
                    }
                } else {
                    if (tempMenor > maiorMenor) {
                        maiorMenor = tempMenor;
                    }
                    antigoTempMenor = tempMenor;
                    tempMenor = (tempMaior+tempMenor)/2;
                }
            }

            if (tempMenor == antigoTempMenor)
                achou = true;
            if (count==1000)
                achou = true;
        }
        return tempMenor;

    }

}
