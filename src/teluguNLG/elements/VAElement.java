package teluguNLG.elements;

import teluguNLG.features.Pos;
import teluguNLG.features.Gender;
import teluguNLG.features.Num;
import teluguNLG.features.Person;
public class VAElement
{
private String verb;
private String tenseMode;
private AdverbElement adv=null;
private Pos pos;
private Gender sgender;
private Num snumber; 
private Person sperson;
private String stype;
private String vtype;

public void setVerb(String verb)
{
this.verb=verb;
}
public String getVerb()
{
return this.verb;

}
public void setTenseMode(String tenseMode)
{
 this.tenseMode=tenseMode;
}
public String getTenseMode()
{
 return this.tenseMode;
}
public void setAdverbElement(AdverbElement adv)
{
 this.adv=adv;
}
public AdverbElement getAdverbElement()
{
return this.adv;
}
public void setPos(Pos pos)
{
 this.pos=pos;
}
public Pos getPos()
{
 return this.pos;
}
public void setGender(Gender sgender)
{
 this.sgender=sgender;
}
public Gender getGender()
{
 return this.sgender;
}
public void setNumber(Num snumber)
{
 this.snumber=snumber;
}
public Num getNumber()
{
 return this.snumber;
}
public void setPerson(Person sperson)
{
 this.sperson=sperson;

}
public Person getPerson()
{
return this.sperson;

}
public void setSType(String stype)
{
 this.stype=stype;
}
public String getSType()
{
 return this.stype;
}
public void setVType(String vtype)
{
 this.vtype=vtype;
}
public String getVType()
{
 return this.vtype;
}


}
