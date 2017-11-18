package Controller;

import Model.IDose;
import Model.IMedicine;
import View.View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IController {
    /**
     * Retrieves the Medicine instance.
     *
     * @return Medicine instance.
     */
    IMedicine getMedicine();

    //Basic Features
    void addDose(LocalDateTime dateTimeTakeDose, double amount, boolean isTest);

    /**
     * Remove a dose by its index.
     *
     * @param index index of dose.
     */
    void removeDose(int index);

    /**
     * Delete all doses from the dosages array.
     */
    void removeAllDoses();

    /**
     * Save Medicine to a file.
     * By default, save directory is user's home directory.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of save file to be saved.
     */
    void saveFile(String filename);

    /**
     * Load a saved Medicine file.
     * By default, load directory is user's home directory.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of the file to be loaded.
     */
    void loadFile(String filename);

    /**
     * Create a new Medicine instance.
     *
     * @param name     Name of medicine
     * @param tMax     TMax of medicine.
     * @param halfLife Half life of medicine.
     */
    void newFile(String name, LocalTime tMax, LocalTime halfLife);

    /**
     * Calculate concentration amount at specified time.
     *
     * @param doseIn       Dose instance.
     * @param dateTimeAtIn Specified time.
     * @return Concentration amount at specific time.
     */
    Double getConcentrationAtTime(IDose doseIn, LocalDateTime dateTimeAtIn);

    /**
     * Sums all dose amount at a time specified by the user
     *
     * @param dateTime Specified time
     * @return Sum of dose amounts at specified time.
     */
    Double getSumConcentrationsAtTime(LocalDateTime dateTime);


    /**
     * Creates a LocalDate instance by parsing a String.
     *
     * @param date Date as a String.
     * @return LocalDate instance.
     */
    LocalDate createLocalDate(String date);

    /**
     * Creates a LocalTime instance by parsing a String.
     *
     * @param time Time as a String.
     * @return LocalTime instance.
     */
    LocalTime createLocalTime(String time);

    //Advanced Features

    /**
     * Removes all test doses.
     */
    void removeTestDoses();

    /**
     * Calculates when Dose is going to reach its peak level of concentration.
     *
     * @param dose Dose instance.
     * @return Time when Dose reaches its peak level.
     */
    LocalDateTime getPeakLevel(IDose dose);

    /**
     * Calculates when to give next dose to reach a certain concentration amount.
     *
     * @param dose         Dose instance.
     * @param amountDoseAt Desired dose concentration amount.
     * @return Time when doses reaches specified amount.
     */
    LocalDateTime getWhenToDose(IDose dose, double amountDoseAt);
}