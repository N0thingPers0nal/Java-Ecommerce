package org.example.view;

import org.example.models.Category;
import org.example.utils.ReadFilesUtils;
import org.example.utils.StringUtil;

import static org.example.utils.ReadFilesUtils.getCategoryArr;
import static org.example.utils.Utils.println;

public class CategeriesPage {
    ReadFilesUtils readFilesUtils;
//    private static ArrayList<Category> categories = new ArrayList<>();
//        try {
//            Scanner sc = new Scanner(new File(getFilePath() + "category.csv"));
//            while (sc.hasNext()) {
//                String[] categoryArr = sc.next().split(",");
//                println("\t\t"+categoryArr[0] + ". " + categoryArr[1]);
////                Category category = new Category(parseInt((categoryArr[0])),categoryArr[1]);
////                categories.add(category);
////                println(String.valueOf(category.get(1)));
////                for (Category category1:categories){
////                    println(category1.getId()+". "+category1.getCategoryName());
////                }
//            }
//            println(StringUtil.BACK);
//            sc.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    public void viewCategories() {
        println(StringUtil.CATEGORY);
        readFilesUtils.category();
        for(Category category: getCategoryArr()){
            println(category.getId()+". "+category.getCategoryName());
        }

    }
}
