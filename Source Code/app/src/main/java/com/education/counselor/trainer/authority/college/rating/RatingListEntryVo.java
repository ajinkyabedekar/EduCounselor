package com.education.counselor.trainer.authority.college.rating;
/*-------------------------------------------------------------------------------------------
 |     Its is a model class for writing data in this module                                   |
 |---------------------------------------------------------------------------------------------
*/
public class RatingListEntryVo {
    private String name, rating;
    RatingListEntryVo() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
}
