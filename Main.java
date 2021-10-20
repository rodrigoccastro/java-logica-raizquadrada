package br.rodrigo;

public class Main {

    private static int count = 0;

    public static void main(String[] args) {

        try {

           for (int i=1; i<=50;i++)
                System.out.println("raiz("+i+"):" + raizQuadrada(i) + ";iterações="+count);

            for (double i=0.1; i<=0.5;i=i+0.1)
                System.out.println("raiz("+i+"):" + raizQuadrada(i) + ";iterações="+count);

        } catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
        }
    }

    private static double raizQuadrada(double valor) throws Exception {

        //fiz este contador para registrar e limitar a qtd de iterações!
        count = 0;

        if (valor <= 0)
            throw new Exception();

        if (valor == 1)
            return 1;

        boolean achou = false;

        //esta variável marca o valor proximo maior
        double tempMaior = valor;
        /*preciso fazer o tratamento específico para valores entre 0 e 1
        porque a excepcionalmente a raiz quadrada é maior do que o numero em si
        ex: raiz de 0.2 = 0.44...
         */
        if ((tempMaior>0) && (tempMaior<1))
            tempMaior = tempMaior * 10;

        //esta variável marca o valor proximo menor
        double tempMenor = valor / 2;

        //precisei criar estas variáveis para evitar repetição por falta de precisão em mais 13 casas decimais
        double maiorMenor = 0;
        double antigoTempMenor = tempMenor;

        while (!achou) {
            count++;
            //System.out.println("maior:"+tempMaior+";menor:"+tempMenor+";mmenor:"+maiorMenor+";count:"+count);

            //checo se já encontrei o valor na temp menor
            double testeMenor = tempMenor * tempMenor;
            if (testeMenor == valor) {
                achou = true;
                return tempMenor;
            }

            //checo se já encontrei o valor na temp maior
            double testeMaior = tempMaior * tempMaior;
            if (testeMaior == valor) {
                achou = true;
                return tempMaior;
            }

            if (!achou) {
                //preciso mover as variaveis tempmenor e/ou tempmaior para ter mais precisão

                if (testeMenor > valor) {
                    //neste caso a tempmenor precisa ser diminuída, pois já é maior que a raiz quadrada
                    //tempmaior terá este novo limite maior
                    tempMaior = tempMenor;
                    //antes de mudar tempmenor, vou guardar o antigo registro
                    antigoTempMenor = tempMenor;
                    //percorro mais para baixo para buscar mais precisão
                    tempMenor = tempMenor / 2;
                    if (tempMenor < maiorMenor) {
                        //se depois desta divisão, o novo valor tiver menos precisao que o registro antigo,
                        // preservo o registro antigo
                        tempMenor = maiorMenor;
                    }
                } else {
                    //neste caso a tempmenor precisa ser aumentada, pois já é menor que a raiz quadrada
                    if (tempMenor > maiorMenor) {
                        // aqui atualizo o registro de maior precisão
                        maiorMenor = tempMenor;
                    }
                    //guardo o registro de tempmenor
                    antigoTempMenor = tempMenor;
                    //busco maior precisao na média entre tempmaior e tempmenor
                    tempMenor = (tempMaior+tempMenor)/2;
                }
            }

            //este if visa apenas repetições longas por precisão acima de 13 casas decimais
            if (tempMenor == antigoTempMenor)
                achou = true;

            //apenas para preservar de um possível loop
            if (count==1200)
                achou = true;

        }
        return tempMenor;

    }

}
