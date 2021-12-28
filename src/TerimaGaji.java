public class TerimaGaji extends Gaji{
    @Override
    public void gajiPokok() {
        switch(jabatan){
            case 1: this.gajiPokok=7000000.0; break;
            case 2: this.gajiPokok=6000000.0; break;
            case 3: this.gajiPokok=5000000.0; break;
            case 4: this.gajiPokok=4000000.0; break;
            case 5: this.gajiPokok=1000000.0; break;
            default: System.out.println("Inputan harus berupa no. Jabatan (angka)");;
        } 
    }
}