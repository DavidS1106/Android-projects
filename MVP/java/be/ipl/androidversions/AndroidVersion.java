/**
 * @author Journaldev.com, Gregory Seront
 */
package be.ipl.androidversions;

public class AndroidVersion {


    String name;
    String version;
    int id_;
    int image;

    public AndroidVersion(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image=image;
    }


    public String getName() {
        return name;
    }


    public String getVersion() {
        return version;
    }

    public int getImageId() {
        return image;
    }

    public int getId() {
        return id_;
    }
}