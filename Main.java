class Train extends Thread {
    private String trainName;
    private int platformNumber;

    public Train(String trainName, int platformNumber) {
        this.trainName = trainName;
        this.platformNumber = platformNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getPlatformNumber() {
        return platformNumber;
    }

    @Override
    public void run() {
        try {
            System.out.println(trainName + " is arriving at Platform " + platformNumber + "...");
            Thread.sleep(2000);// so that it can wait a bit

            System.out.println(trainName + " is boarding passengers...");
            Thread.sleep(2000);

            System.out.println(trainName + " is departing from Platform " + platformNumber + "...");

        } catch (InterruptedException e) {
            System.out.println(trainName + " was cancelled during operation!");
        }
    }
}

