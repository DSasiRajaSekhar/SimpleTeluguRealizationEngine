package teluguNLG.elements;

import teluguNLG.features.Pos;
import teluguNLG.features.Gender;
import teluguNLG.features.Num;
import teluguNLG.features.Person;
import teluguNLG.features.AdjectiveType;
public class SOCElement
{
 private String soc;
 private String caseMarker;
 private String type;
 private String honorific;
 private AdjectiveElement adj=null;
 private QuantifierElement quantifier=null;
 private ProperNounCompoundElement properNounCompound=null;
 private PossessiveElement possessive=null;
 private Pos pos;
 private Gender gender;
 private Num number;
 private Person person;

public void setSOC(String soc)
{
 this.soc=soc;

} 
public String getSOC()
{
 return this.soc;

}
public void setCaseMarker(String caseMarker)
{
 this.caseMarker=caseMarker;

}
public String getCaseMarker()
{
 return this.caseMarker;
}
public void setType(String type)
{
 this.type=type;
}
public String getType()
{
 return this.type;
}
public void setHonorific(String honorific)
{
 this.honorific=honorific;
}
public String getHonorific()
{
 return this.honorific;
}
public void setAdjectiveElement(AdjectiveElement adj)
{
 this.adj=adj;
}
public AdjectiveElement getAdjectiveElement()
{
 return this.adj;

}
public void setQuantifierElement(QuantifierElement quantifier)
{
 this.quantifier=quantifier;
}
public QuantifierElement getQuantifierElement()
{
 return this.quantifier;
}
public void setProperNounCompoundElement(ProperNounCompoundElement properNounCompound)
{
 this.properNounCompound=properNounCompound;
}
public ProperNounCompoundElement getProperNounCompoundElement()
{
 return this.properNounCompound;
}
public void setPossessiveElement(PossessiveElement possessive)
{
 this.possessive=possessive;
}
public PossessiveElement getPossessiveElement()
{
 return this.possessive;
}
public void setPos(Pos pos)
{
 this.pos=pos;
}
public Pos getPos()
{
 return this.pos;
}
public void setGender(Gender gender)
{
 this.gender=gender;
}
public Gender getGender()
{
 return this.gender;
}
public void setNumber(Num number)
{
 this.number=number;
}
public Num getNumber()
{
 return this.number;
}
public void setPerson(Person person)
{
 this.person=person;

}
public Person getPerson()
{
return this.person;

}

}

