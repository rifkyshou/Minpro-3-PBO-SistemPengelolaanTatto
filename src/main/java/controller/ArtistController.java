/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.TattooArtist;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ArtistController {
    private final ArrayList<TattooArtist> daftarArtist = new ArrayList<>();
 
    public ArtistController() {
        seedData();
    }
 
    private void seedData() {
        tambahArtist("Rian Ink", "0821767686", "Realis");
        tambahArtist("Dewi Tattoo", "082145456655", "Minimalis");
        tambahArtist("Bima Tribal", "081234567890", "Tribal");
    }
 
    public TattooArtist tambahArtist(String nama, String noHp, String spesialisasi) {
        TattooArtist artist = new TattooArtist(nama, noHp, spesialisasi);
        daftarArtist.add(artist);
        return artist;
    }
 
    public ArrayList<TattooArtist> getAll() {
        return daftarArtist;
    }
}
