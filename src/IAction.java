//TakeADose implements IAction, SpitOutADose implements IAction, TestDoseAction implements IAction
public interface IAction {
    /**
     *
     * @param medicine
     */
    void createActionWith(IMedicine medicine);

    /**
     *
     */
    void doSomething();
}