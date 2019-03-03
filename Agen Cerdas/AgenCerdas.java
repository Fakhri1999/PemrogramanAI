public class AgenCerdas {
    private String lingkungan[][];
    AgenCerdas(int location){
        lingkungan = new String[location][3];
    }
    public void setKondisi(int i, String Nama,String location,String status){
        lingkungan[i-1][0]=Nama;
        lingkungan[i-1][1]=location;
        lingkungan[i-1][2]=status;
    }
    public void vacumm (int i,String location, String status){
        if ("kotor".equals(status.toLowerCase())){
            System.out.println("----bersih-bersih----");
            status = "bersih";
            lingkungan[i][2]=status;
            vacumm(i,location,status);
        }else if ("kiri".equals(location.toLowerCase().substring(0,4))){
            switch (location.toLowerCase().substring(5)) {
                case "atas":
                    System.out.println("geser bawah");
                    System.out.println("v");
                    System.out.println("v");
                    break;
                case "tengah":
                    System.out.println("geser bawah");
                    System.out.println("v");
                    System.out.println("v");
                    break;
                case "bawah":
                    System.out.println("geser kanan-->");
                    break;
            }
        }else if ("kanan".equals(location.toLowerCase().substring(0,5))){
            switch (location.toLowerCase().substring(6)) {
                case "atas":
                    System.out.println("<--geser kiri");
                    break;
                case "tengah":
                    System.out.println("^");
                    System.out.println("^");
                    System.out.println("geser atas");
                    break;
                case "bawah":
                    System.out.println("^");
                    System.out.println("^");
                    System.out.println("geser atas");
                    break;
            }
        }
    }
    public void bersihkan(){
        for (int i = 0; i<lingkungan.length; i++){
            System.out.println("\n" + lingkungan[i][0]);
            vacumm(i,lingkungan[i][1],lingkungan[i][2]);
        }
        cek(); 
    }
    public void cek (){
        for (int i = 0; i<lingkungan.length; i++){
            if(!"bersih".equals(lingkungan[i][2])){
                bersihkan();
                break;	
            }
        }
        System.out.println("SEMUA SUDAH BERSIH");
    }
    public static void main(String[] args) {
        AgenCerdas a = new AgenCerdas(6);
        a.setKondisi(1, "Lokasi 1", "kiri-atas", "kotor");
        a.setKondisi(2, "Lokasi 2", "kiri-tengah", "kotor");
        a.setKondisi(3, "Lokasi 3", "kiri-bawah", "bersih");
        a.setKondisi(4, "Lokasi 4", "kanan-bawah", "kotor");
        a.setKondisi(5, "Lokasi 5", "kanan-tengah", "kotor");
        a.setKondisi(6, "Lokasi 6", "kanan-atas", "bersih");
        a.bersihkan();
    }
}