package by.epam.task02.main;

import by.epam.task02.entity.Dish;
import by.epam.task02.entity.Section;
import by.epam.task02.entity.Menu;
import by.epam.task02.service.OwnDOMParser;
import by.epam.task02.domain.Document;
import by.epam.task02.domain.Element;

import java.util.List;

public class Main {

    private static final String xmlFileURI = "xml\\menu.xml";

    public static void main(String[] args) {

    	Menu menu = new Menu();

    	OwnDOMParser ownDOMParser = new OwnDOMParser(xmlFileURI);
        Document document = ownDOMParser.parse();
        Element menuElement = document.getDocumentElement();

        List<Element> sectionElements = menuElement.getElementsByTagName("section");
        for(Element sectionElement : sectionElements) {
            Section section = new Section();
            section.setName(sectionElement.getElementsByTagName("section-name").get(0).getInnerText());
            menu.addSection(section);

            List<Element> dishElements = sectionElement.getElementsByTagName("dish");
            for(Element dishElement : dishElements) {
                Dish dish = new Dish();
                try {
                dish.setPhoto(dishElement.getElementsByTagName("dish-photo").get(0).getInnerText());
                dish.setName(dishElement.getElementsByTagName("dish-name").get(0).getInnerText());
                dish.setDescription(dishElement.getElementsByTagName("dish-description").get(0).getInnerText());
                dish.setPortion(dishElement.getElementsByTagName("dish-portion").get(0).getInnerText());
                dish.setPrice(Integer.parseInt(dishElement.getElementsByTagName("dish-price").get(0).getInnerText()));
                } catch (Exception ex) {
                	
                }
             
                section.addDish(dish);
            }
        }

        System.out.println("МЕНЮ:\n");
        for (Section section : menu.getSections()) {
            System.out.println(section.getName());
            for (Dish dish : section.getDishes()) {
                System.out.println(
                		"\tНазвание: " + dish.getName() +
                		"\n\tОписание: " + (dish.getDescription() != null ? dish.getDescription() : "отсутствует") +
                		"\n\tПорция: " + dish.getPortion() + 
                		"\n\tЦена: " + (dish.getPrice() != 0 ? dish.getPrice() : "не указана") +
                		"\n\tФото: " + dish.getPhoto() +
                		"\n"); 		
            }
        }
    }

}