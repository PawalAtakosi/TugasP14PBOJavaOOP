import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {
    public static Integer noPegawai;
    public static String nama;
    public static Integer noJabatan;
    public static Double gajiPokok;
    public static Integer hariMasuk;
    public static Double potonganGaji;
    public static Double totalGaji;
    public static String jabatan;
    static Connection conn;
    public static void main(String[] args) {
        try (Scanner terimaInput = new Scanner (System.in)) {
            String pil;
            boolean isLanjutkan = true;

            String url = "jdbc:mysql://localhost:3306/tugas14";
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	conn = DriverManager.getConnection(url,"root","");
            	System.out.println("Class Driver ditemukan");
            	
            	while (isLanjutkan) {
            		System.out.println("\n================");
            		System.out.println("Database Pegawai");
            		System.out.println("================");
            		System.out.println("1. Lihat Data ");
            		System.out.println("2. Tambah Data ");
            		System.out.println("3. Ubah Data ");
            		System.out.println("4. Hapus Data ");
            		System.out.println("5. Cari Data ");
            		
            		System.out.print("\nPilihan anda (1/2/3/4/5): ");
            		pil = terimaInput.next();
            		
            		switch (pil) {
            			case "1":
            				lihatdata();
            				break;
            			case "2":
            				tambahdata();
            				break;
            			case "3":
            				ubahdata();
            				break;
            			case "4":
            				hapusdata();
            				break;
            			case "5":
            				caridata();
            				break;
            			default:
            				System.err.println("\nInput tidak ditemukan\n pilih [1-5]");
            		}
            		
            		System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
            		pil = terimaInput.next();
            		isLanjutkan = pil.equalsIgnoreCase("y");
            	}
            	System.out.println("\nTerimakasih telah menggunakan layanan ini..");
            	
            }
            catch(ClassNotFoundException ex) {
            	System.err.println("Driver Error");
            	System.exit(0);
            }
            catch(SQLException e){
            	System.err.println("Koneksi tidak berhasil");
            }
        }
	}
    private static void lihatdata() throws SQLException {
		String text1 = "\n~~Data Pegawai Saat Ini~~";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM pegawai";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNo. Pegawai\t\t: ");
            System.out.print(result.getInt("noPegawai"));
            System.out.print("\nNama\t\t\t: ");
            System.out.print(result.getString("nama"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nGaji pokok\t\t: ");
            System.out.print(result.getDouble("gajiPokok"));
            System.out.print("\nJumlah hari masuk\t: ");
            System.out.print(result.getInt("hariMasuk"));
            System.out.print("\nPotongan\t\t: ");
            System.out.print(result.getDouble("potonganGaji"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.print(result.getDouble("totalGaji"));
            System.out.print("\n");
		}
	}
    private static void tambahdata() throws SQLException{
		String text2 = "\n~~Tambah Data Pegawai~~";
		System.out.println(text2.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            try {
            
                System.out.print("No. pegawai\t\t: ");
                noPegawai = terimaInput.nextInt();
                System.out.print("Nama\t\t\t: ");
                String nama = terimaInput.next();
                System.out.println("\n~~Pilihan jabatan~~");
                System.out.println("1 Direksi");
                System.out.println("2 Direktur");
                System.out.println("3 Manager");
                System.out.println("4 Employee");
                System.out.println("5 Cleaning Service");
                
                do{
                    System.out.println("Jabatan\t\t\t: ");
                    noJabatan= terimaInput.nextInt();
                }
                while(noJabatan<1||noJabatan>5);
                
                if(noJabatan==1){
                    jabatan="Direksi";  
                }else if(noJabatan==2){
                    jabatan="Direktur";  
                }else if(noJabatan==3){
                    jabatan="Manager";  
                }else if(noJabatan==4){
                    jabatan="Employee";  
                }else if(noJabatan==5){
                    jabatan="Cleaning Service";  
                }
                
                do{
                    System.out.println("Jumlah hari masuk\t: ");
                        hariMasuk= terimaInput.nextInt();
                }while(hariMasuk<1||hariMasuk>30);
                
                switch(noJabatan){
                    case 1: gajiPokok=5000000.0; break;
                    case 2: gajiPokok=4000000.0; break;
                    case 3: gajiPokok=3000000.0; break;
                    case 4: gajiPokok=2000000.0; break;
                    case 5: gajiPokok=1000000.0; break;
                    default: System.out.println("Masukkan angka yang sesuai");
                } 
                potonganGaji = ((double)(30-hariMasuk)/30)*gajiPokok;
                totalGaji = gajiPokok-potonganGaji;
            
            String sql = "INSERT INTO pegawai (noPegawai, nama, jabatan, gajiPokok, hariMasuk, potonganGaji, totalGaji) VALUES ('"+noPegawai+"','"+nama+"','"+jabatan+"','"+gajiPokok+"','"+hariMasuk+"','"+potonganGaji+"','"+totalGaji+"')";
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data");

            } catch (SQLException e) {
                System.err.println("Terjadi kesalahan input data");
            } catch (InputMismatchException e) {
            	System.err.println("Inputan harus berupa angka");
            }
        }
	}
    private static void ubahdata() throws SQLException{
		String text3 = "\n~~~Ubah Data Pegawai~~";
		System.out.println(text3.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            try {
                lihatdata();
                System.out.print("Masukkan No Pegawai yang akan diperbaharui : ");
                Integer noPegawai = Integer.parseInt(terimaInput.nextLine());
                
                String sql = "SELECT * FROM pegawai WHERE noPegawai = " +noPegawai;
                
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                
                if(result.next()){
                    
                    System.out.print("\nNama ["+result.getString("nama")+"]\t\t: ");
                        nama = terimaInput.nextLine();
                        
                        System.out.println("\n~~Pilihan jabatan~~");
                        System.out.println("1 jika anda seorang Direksi");
                        System.out.println("2 jika anda seorang Direktur");
                        System.out.println("3 jika anda seorang Manager");
                        System.out.println("4 jika anda seorang Employee");
                        System.out.println("5 jika anda seorang Cleaning Service");
                        
                        do{
                            System.out.println("Jabatan["+result.getString("jabatan")+"]\t: ");
                                noJabatan= terimaInput.nextInt();
                        }while(noJabatan<1||noJabatan>5);

                        if(noJabatan==1){
                            jabatan="Direksi";  
                        }else if(noJabatan==2){
                            jabatan="Direktur";  
                        }else if(noJabatan==3){
                            jabatan="Manager";  
                        }else if(noJabatan==4){
                            jabatan="Employee";  
                        }else if(noJabatan==5){
                            jabatan="Cleaning Service";  
                        }

                        switch(noJabatan){
                            case 1: gajiPokok=5000000.0; break;
                            case 2: gajiPokok=4000000.0; break;
                            case 3: gajiPokok=3000000.0; break;
                            case 4: gajiPokok=2000000.0; break;
                            case 5: gajiPokok=1000000.0; break;
                            default: System.out.println("Masukkan angka yang sesuai");
                        } 
                        do{
                            System.out.println("Jumlah hari masuk["+result.getInt("hariMasuk")+"]\t: ");
                                hariMasuk= terimaInput.nextInt();
                        }while(hariMasuk<1||hariMasuk>30);

                        potonganGaji = ((double)(30-hariMasuk)/30)*gajiPokok;
                        totalGaji = gajiPokok-potonganGaji;
                    sql = "UPDATE pegawai SET nama='"+nama+"',jabatan= '"+jabatan+"',gajiPokok= '"+gajiPokok+"',hariMasuk= '"+hariMasuk+"',potonganGaji= '"+potonganGaji+"',totalGaji= '"+totalGaji+"' WHERE noPegawai='"+noPegawai+"'";

                    if(statement.executeUpdate(sql) > 0){
                        System.out.println("Berhasil memperbaharui data Pegawai No. "+noPegawai);
                    }
                }
                statement.close();        
            } catch (SQLException e) {
                System.err.println("Terjadi kesalahan dalam memperbaharui data");
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
	}
    private static void hapusdata() {
		String text4 = "\n~~Hapus Data Pegawai~~";
		System.out.print(text4.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            try{
                lihatdata();
                System.out.print("Ketik No. pegawai yang akan Anda Hapus : ");
                Integer noPegawai= Integer.parseInt(terimaInput.nextLine());
                
                String sql = "DELETE FROM pegawai WHERE noPegawai = "+ noPegawai;
                Statement statement = conn.createStatement();
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil menghapus data Pegawai No. "+noPegawai);
                }
            }catch(SQLException e){
                System.out.println("Terjadi kesalahan dalam menghapus data ");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
	}
    private static void caridata () throws SQLException {
		String text5 = "\n~~Cari Data Pegawai~~";
		System.out.println(text5.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            System.out.print("Masukkan Nama Pegawai : ");
            
            String keyword = terimaInput.nextLine();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM pegawai WHERE nama LIKE '%"+keyword+"%'";
            ResultSet result = statement.executeQuery(sql); 
                    
            while(result.next()){
                System.out.print("\nNo Pegawai\t\t: ");
                System.out.print(result.getInt("noPegawai"));
                System.out.print("\nNama\t\t\t: ");
                System.out.print(result.getString("nama"));
                System.out.print("\nJabatan\t\t\t: ");
                System.out.print(result.getString("jabatan"));
                System.out.print("\nGaji pokok\t\t: ");
                System.out.print(result.getDouble("gajiPokok"));
                System.out.print("\nJumlah hari masuk\t: ");
                System.out.print(result.getInt("hariMasuk"));
                System.out.print("\nPotongan\t\t: ");
                System.out.print(result.getDouble("potongan"));
                System.out.print("\nTotal gaji\t\t: ");
                System.out.print(result.getDouble("totalGaji"));
                System.out.print("\n");
            }
        }
	}
}