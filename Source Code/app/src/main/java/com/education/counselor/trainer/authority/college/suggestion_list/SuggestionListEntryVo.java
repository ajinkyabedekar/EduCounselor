package com.education.counselor.trainer.authority.college.suggestion_list;
/*
  
******************************************************************
* this is model class for retriving datas of coursefrom firebase *
******************************************************************
*************************************
*      Biren Sharma         *
*************************************
*/
public class SuggestionListEntryVo {
    private String name, phone;
    SuggestionListEntryVo() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
