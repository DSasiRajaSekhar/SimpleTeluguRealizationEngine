package teluguNLG.elements;

import teluguNLG.features.Pos;
import teluguNLG.features.AdjectiveType;
public class AdjectiveElement
{
 private String adjective;
 private String suffix;
 private Pos pos;
 private AdjectiveType adjectiveType;

public void setAdjective(String adjective)
{
 this.adjective=adjective;

}
public String getAdjective()
{
 return this.adjective;
}
public void setSuffix(String suffix)
{
 this.suffix=suffix;

}
public String getSuffix()
{
 return this.suffix;
}
 
public void setPos(Pos pos)
{
 this.pos=pos;
}
public Pos getPos()
{
 return this.pos;
}
public void setAdjectiveType(AdjectiveType adjectiveType)
{
 this.adjectiveType=adjectiveType;
}
public AdjectiveType getAdjectiveType()
{
 return this.adjectiveType;
}


}
