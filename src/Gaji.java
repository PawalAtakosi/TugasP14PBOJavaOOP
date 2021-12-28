import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Gaji implements PTABC{
    public Integer noPegawai;
    public String namaPegawai;
    public Integer jabatan;
    public Double gajiPokok;
    public Integer jumlahHariMasuk;
    public Double potongan;
    public Double totalGaji;
    public String namaJabatan;
    
    Scanner scanner= new Scanner(System.in);

    @Override
    public void namaPegawai() {
        System.out.println("Nama : ");
        this.namaPegawai= scanner.nextLine();  
    }

    @Override
    public void noPegawai() {
        System.out.println("No Pegawai : ");
        this.noPegawai = scanner.nextInt();  
    }
    
    @Override
    public void jabatan() {
        System.out.println("==Pilihan jabatan==");
        System.out.println("1 Direksi");
        System.out.println("2 Direktur");
        System.out.println("3 Manager");
        System.out.println("4 Employee");
        System.out.println("5 Cleaning Service");
        do{
            System.out.println("Jabatan : ");
            this.jabatan= scanner.nextInt();
        }while(this.jabatan<1||this.jabatan>5);
        
    }

    @Override
    public void gajiPokok() {
        switch(this.jabatan){
            case 1: this.gajiPokok=5000000.0; break;
            case 2: this.gajiPokok=4000000.0; break;
            case 3: this.gajiPokok=3000000.0; break;
            case 4: this.gajiPokok=2000000.0; break;
            case 5: this.gajiPokok=1000000.0; break;
            default: System.out.println("Masukkan angka yang sesuai");
        } 
    }

    @Override
    public void jumlahHariMasuk() {
        do{
            System.out.println("Jumlah hari masuk : ");
            this.jumlahHariMasuk = scanner.nextInt();
        }while(this.jumlahHariMasuk<1||this.jumlahHariMasuk>30);
    }

    @Override
    public Double potongan(){
        this.potongan=((double)(30-this.jumlahHariMasuk)/30)*this.gajiPokok;
        return this.potongan;
    }
    @Override
    public Double totalGaji(){
        this.totalGaji=this.gajiPokok-this.potongan;
        return this.totalGaji;
    }

    public void tampil(){
        LocalDateTime tanggalAkses= LocalDateTime.now();
        System.out.println("\nData Pegawai Saat Ini");
        System.out.println("Diakses Pada\t: "+tanggalAkses.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));;
        System.out.println("Nama Pegawai\t: "+this.namaPegawai);
        System.out.println("No Pegawai\t: "+this.noPegawai);
        if(this.jabatan==1){
            namaJabatan="Direksi";  
        }else if(this.jabatan==2){
            namaJabatan="Direktur";  
        }else if(this.jabatan==3){
            namaJabatan="Manager";  
        }else if(this.jabatan==4){
            namaJabatan="Employee";  
        }else if(this.jabatan==5){
            namaJabatan="Cleaning Service";  
        }
        System.out.println("Jabatan\t\t: "+namaJabatan);
        System.out.println("Gaji Pokok\t: Rp"+this.gajiPokok);
        System.out.println("Jumlah Presensi\t: "+this.jumlahHariMasuk);
        System.out.println("Potongan Gaji\t: Rp"+this.potongan);
        System.out.println("Gaji Bulan Ini\t: Rp"+this.totalGaji);
    }
}