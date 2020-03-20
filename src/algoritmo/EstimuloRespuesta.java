package algoritmo;

public class EstimuloRespuesta {
    int[][] posEspeciales;
    int filas, cols;
    int[][] arr;
    
    public EstimuloRespuesta(){}
    
    public EstimuloRespuesta(int filas, int cols, int[][] arr, int[][] posEspeciales) {
        this.filas = filas;
        this.cols = cols;
        this.arr = arr;
        this.posEspeciales = posEspeciales;
    }
    
    public Coordenada sigPosicion(int filas, int cols, int[][] arr, int[][] posEspeciales){
        //this.filas = filas;
        //this.cols = cols;
        //this.arr = arr;
        //this.posEspeciales = posEspeciales;
        int i = posEspeciales[2][0];
        int j = posEspeciales[2][1];
        Coordenada coordAnterior = new Coordenada(0,0);
        Coordenada coordNuevo = new Coordenada(0,0);
        
        if ((i > 0) && (i < filas - 1)) { //
            if ((j < cols - 1) && (j > 0)) {
                if ((arr[i - 1][j] <= arr[i][j + 1]) && (arr[i - 1][j] <= arr[i + 1][j]) && (arr[i - 1][j] <= arr[i][j - 1]) && (arr[i - 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i-1, j);
                    posEspeciales[2][0] = i - 1; //Nuevo origen
                    posEspeciales[2][1] = j; //Nuevo origen
                } else if ((arr[i][j + 1] <= arr[i + 1][j]) && (arr[i][j + 1] <= arr[i][j - 1]) && (arr[i][j + 1] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j+1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j + 1;
                } else if ((arr[i + 1][j] <= arr[i][j - 1]) && (arr[i + 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i + 1;
                    posEspeciales[2][1] = j;
                } else if (arr[i][j - 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j-1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j - 1;
                }
            } else if (j == 0) { //PRIMERA COLUMNA
                if ((arr[i - 1][j] <= arr[i][j + 1]) && (arr[i - 1][j] <= arr[i + 1][j]) && (arr[i - 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i-1, j);
                    posEspeciales[2][0] = i - 1;
                    posEspeciales[2][1] = j;
                } else if ((arr[i][j + 1] <= arr[i - 1][j]) && (arr[i][j + 1] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j+1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j + 1;
                } else if (arr[i + 1][j] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i + 1;
                    posEspeciales[2][1] = j;
                }
            } else if (j == cols - 1) { //ULTIMA COLUMNA
                if ((arr[i - 1][j] <= arr[i - 1][j]) && (arr[i - 1][j] <= arr[i][j - 1]) && (arr[i - 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i-1, j);
                    posEspeciales[2][0] = i - 1;
                    posEspeciales[2][1] = j;
                } else if ((arr[i + 1][j] <= arr[i][j - 1]) && (arr[i + 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i + 1;
                    posEspeciales[2][1] = j;
                } else if (arr[i][j - 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j-1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j - 1;
                }
            }
        } else if (i == 0) { //FILA 0

            if ((j > 0) && (j < cols - 1)) {
                if ((arr[i][j + 1] <= arr[i + 1][j]) && (arr[i][j + 1] <= arr[i][j - 1]) && (arr[i][j + 1] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j+1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j + 1;
                } else if ((arr[i + 1][j] <= arr[i][j - 1]) && (arr[i + 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i + 1;
                    posEspeciales[2][1] = j;
                } else if (arr[i][j - 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j-1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j - 1;
                }
            } else if (j == 0) {
                if ((arr[i][j + 1] <= arr[i + 1][j]) && (arr[i][j + 1] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j+1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j + 1;
                } else if (arr[i + 1][j] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i + 1;
                    posEspeciales[2][1] = j;
                }
            } else if (j == cols - 1) {
                if ((arr[i + 1][j] <= arr[i][j - 1]) && (arr[i + 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i + 1;
                    posEspeciales[2][1] = j;
                } else if (arr[i][j - 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j-1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j - 1;
                }
            }
        } else if (i == filas - 1) {
            if ((j > 0) && (j < cols - 1)) {
                if ((arr[i - 1][j] <= arr[i][j + 1]) && (arr[i - 1][j] <= arr[i][j - 1]) && (arr[i - 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i-1, j);
                    posEspeciales[2][0] = i - 1;
                    posEspeciales[2][1] = j;
                } else if ((arr[i][j + 1] <= arr[i][j - 1]) && (arr[i][j + 1] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i+1, j);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j + 1;
                } else if (arr[i][j - 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j-1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j - 1;
                }
            } else if (j == 0) {
                if ((arr[i - 1][j] <= arr[i][j + 1]) && (arr[i - 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i-1, j);
                    posEspeciales[2][0] = i - 1;
                    posEspeciales[2][1] = j;
                } else if (arr[i][j + 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j+1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j + 1;
                }
            } else if (j == cols - 1) {
                if ((arr[i - 1][j] <= arr[i][j - 1]) && (arr[i - 1][j] != -1)) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i-1, j);
                    posEspeciales[2][0] = i - 1;
                    posEspeciales[2][1] = j;
                } else if (arr[i][j - 1] != -1) {
                    arr[i][j]++;
                    coordAnterior = new Coordenada(i, j);
                    coordNuevo = new Coordenada(i, j-1);
                    posEspeciales[2][0] = i;
                    posEspeciales[2][1] = j - 1;
                }
            }
        }
        
        return coordNuevo;
    }
}
