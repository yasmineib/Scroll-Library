package project;

import project.UI.Preview;
import project.database.SelectRecords;

import java.util.ArrayList;
import java.util.Random;

public class ScrollOfTheDay {
    ArrayList<String> scrollNames = new ArrayList<>();

    public void getScrollNamesFromDB()
    {
        scrollNames = SelectRecords.selectScrollNamesWoPassword();
    }

    public String getRandomScroll()
    {
        String scrollName = "";
        Random rand = new Random();
        Boolean scrollFound = false;
        int randomIndex = rand.nextInt(scrollNames.size());
        scrollName = scrollNames.get(randomIndex);

//
        System.out.println("Random scroll: " + scrollName);

        return scrollName;
    }

    public boolean freeScrollsAvailable()
    {
        if(scrollNames.size() == 0)
        {
            return false;
        }
        return true;
    }

    public void printScroll()
    {
        getScrollNamesFromDB();
        String scrollName = getRandomScroll();
//        need to have a list with all the scrolls here - will get it from the db?


//            just testing to see if Im getting the scrollnames properly
       System.out.println("!!!SCROLL OF THE DAY!!!");
//       getScrollNamesFromDB();
//        for(String name: scrollNames)
//        {
//            System.out.println(name);
//        }
        if(freeScrollsAvailable())
        {
            System.out.println("Scroll name: "+ scrollName);
            Preview preview = new Preview();
            preview.previewFile(scrollName);
        }
        else
        {
            System.out.println("There are no free scrolls to display :(");
        }

        System.out.println("SCROLL OF THE DAY FIN :)");

    }
}
