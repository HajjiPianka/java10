package main.java.zad11;
class Data {
    private String original;
    private String translation;
    Data(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }
    void Lolek() {

    }
    void Lolek(int cos) {

    }
    void Lolek(float cos) {

    }
    String getOriginal() {
        return original;
    }
    void setOriginal(String original) {
        this.original = original;
    }
    String getTranslation() {
        return translation;
    }
    void setTranslation(String translation) {
        this.translation = translation;
    }
    @Override
    public String toString() {
        return original + ";" + translation;
    }
}
