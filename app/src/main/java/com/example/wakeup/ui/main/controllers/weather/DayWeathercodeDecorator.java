package com.example.wakeup.ui.main.controllers.weather;

public class DayWeathercodeDecorator extends WeatherDecorator{
    private String weathercode;

    public DayWeathercodeDecorator(Weather weather, String condition) {
        super(weather);
        this.weathercode = condition;
    }

    public String printWeather(){
        String weathercodeName;
        switch (weathercode) {
            case "0":
                weathercodeName = "Czyste niebo";
                break;
            case "1":
                weathercodeName = "Głównie czyste niebo";
                break;
            case "2":
                weathercodeName = "Niebo częściami zachmurzone";
                break;
            case "3":
                weathercodeName = "Pochmurnie";
                break;
            case "45":
                weathercodeName = "Mgła";
                break;
            case "48":
                weathercodeName = "Mgła i szron";
                break;
            case "51":
                weathercodeName = "Lekka mżawka";
                break;
            case "53":
                weathercodeName = "Umiarkowana mżawka";
                break;
            case "55":
                weathercodeName = "Intensywna mżawka";
                break;
            case "56":
                weathercodeName = "Lekka marznąca mrzawka";
                break;
            case "57":
                weathercodeName = "Intensywna marznąca mżawka";
                break;
            case "61":
                weathercodeName = "Lekki deszcz";
                break;
            case "63":
                weathercodeName = "Umiarkowany deszcz";
                break;
            case "65":
                weathercodeName = "Intensywny deszcz";
                break;
            case "66":
                weathercodeName = "Lekki marznący deszcz";
                break;
            case "67":
                weathercodeName = "Intensywny marznący deszcz";
                break;
            case "71":
                weathercodeName = "Lekkie opady śniegu";
                break;
            case "73":
                weathercodeName = "Umiarkowane opady śniegu";
                break;
            case "75":
                weathercodeName = "Intensywne opady śniegu";
                break;
            case "77":
                weathercodeName = "Śnieżyca";
                break;
            case "80":
                weathercodeName = "Przelotnie lekki deszcz";
                break;
            case "81":
                weathercodeName = "Przelotnie umiarkowany deszcz";
                break;
            case "82":
                weathercodeName = "Przelotnie intensywny deszcz";
                break;
            case "85":
                weathercodeName = "Przelotnie niewielkie opady śniegu";
                break;
            case "86":
                weathercodeName = "Przelotnie silne opady śniegu";
                break;
            case "95":
                weathercodeName = "Umiarkowana burza";
                break;
            case "96":
                weathercodeName = "Burza z niewielkim gradem";
                break;
            case "99":
                weathercodeName = "Burza z silnym gradem";
                break;
            default:
                weathercodeName = "Brak informacji o pogodzie";
        }


        return super.printWeather() + weathercodeName + "\n";
    }
}
