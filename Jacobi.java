public class Jacobi { 
    public static void main(String[] args) {
        float[][] augmentedMatrix = 
        {
            {7, 1, -1, 2, 3},
            {1, 8, 0, -2, -5},
            {-1, 0, 4, -1, 4},
            {2, -2, -1, 6, -3}
        };
        int iteration = 25;
        printArray(jacobi(iteration, augmentedMatrix));
        printArray(gauss(iteration, augmentedMatrix));
        printArray(sor(iteration, augmentedMatrix));
    }

    public static float[] jacobi(int n, float[][] a)  {
        float[][] coe = coefficient(a); 
        float[] px = {0,0,0,0};
        float[] x = new float[4]; 
        
        for (int loop = 0;loop<n;loop++) {
            for (int i = 0;i<4;i++) {
                float sum = 0;
                for (int j=0;j<5;j++) {
                    if (j!=i) {
                        if (j==4) {
                            sum+=coe[i][j];
                        } 
                        else {
                            sum+=(px[j]*coe[i][j]);
                        }
                    }
                }
                x[i] = sum;
            }
            for (int i = 0;i<4;i++) {
                px[i] = x[i];
            }
        }
        return x;
    }

    
    public static float[] gauss(int n, float[][] a)  {
        float[][] coe = coefficient(a);
        float[] px = {0,0,0,0};
        float[] x = new float[4]; 
        
        for (int loop = 0;loop<n;loop++) {
            for (int i = 0;i<4;i++) {
                float sum = 0;
                for (int j=0;j<5;j++) {
                    if (j!=i) {
                        if (j==4) {
                            sum+=coe[i][j];
                        } 
                        else {
                            sum+=(px[j]*coe[i][j]);
                        }
                    }
                }
                x[i] = sum;
                px[i] =  x[i];
            }
        }
        return x;
    }


    public static float[] sor(int n, float[][] a)  {
        float w = 1.1f;
        float[][] coe = coefficient(a);
        float[] ppx = {0,0,0,0};
        float[] px = {0,0,0,0};
        float[] x = new float[4]; 
        
        for (int loop = 0;loop<n;loop++) {
            for (int i = 0;i<4;i++) {
                float sum = 0;
                for (int j=0;j<5;j++) {
                    if (j!=i) {
                        if (j==4) {
                            sum+=coe[i][j];
                        } 
                        else {
                            sum+=(px[j]*coe[i][j]);
                        }
                    }
                }
                x[i] = sum;
                px[i] = x[i];
            }
            for (int i = 0;i<4;i++) {
                x[i] = w*x[i]+(1-w)*ppx[i];
            }
            for (int i = 0;i<4;i++) {
                px[i] = x[i];
                ppx[i] = x[i];
            }
        }
        return x;
    }

    public static float[][] coefficient(float[][] a) {
        float[][] coe = new float[4][5];
        for (int i = 0;i<4;i++) {
            for (int j = 0;j<5;j++) {
                if (i!=j) {
                    coe[i][j] = -a[i][j]/a[i][i];
                    if (j==4) {
                        coe[i][j] = -coe[i][j];
                    }
                }
                else {
                    coe[i][j] = a[i][j];
                }
            }
        }
        return coe;
    }
    
    public static void printArray(float[] a) {
        System.out.print("[");
        for (int i = 0;i<a.length;i++) {
            if (i!=0) {
                System.out.print(", ");
            }
            System.out.print(a[i]);
        }
        System.out.print("]");
        System.out.println();
    }

}