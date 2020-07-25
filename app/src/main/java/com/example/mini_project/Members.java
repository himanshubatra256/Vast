package com.example.mini_project;

public class Members {
    private String uid;
public static String photo;

    private String pas;
    private String pno;

    private String vid;
    private String fname;

    private String driving;
    private String insurance;
    private String poll;
    private String rcertificate;

    public String getDriving() {
        return driving;
    }

    public void setDriving(String driving) {
        this.driving = driving;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPoll() {
        return poll;
    }

    public void setPoll(String poll) {
        this.poll = poll;
    }

    public String getRcertificate() {
        return rcertificate;
    }

    public void setRcertificate(String rcertificate) {
        this.rcertificate = rcertificate;
    }
    public String getPhoto() {
        return photo;
    }
//#F84545


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
        photo=uid;


    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public Members() {

    }
}
