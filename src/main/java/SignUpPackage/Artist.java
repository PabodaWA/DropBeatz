package SignUpPackage;

public class Artist extends User{
	
	private String artistName;
    private String bio;
    
    public Artist() {
        super();
    }
    
    public Artist(int id, String username, String password, String email, String artistName, String bio) {
        super(id, username, password, email, "artist");
        this.artistName = artistName;
        this.bio = bio;
    }
    
    // Getters and Setters
    public String getArtistName() {
        return artistName;
    }
    
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
	
	

}
