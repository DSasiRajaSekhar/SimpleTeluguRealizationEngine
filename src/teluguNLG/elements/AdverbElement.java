package teluguNLG.elements;

import teluguNLG.features.Pos;

public class AdverbElement
{
 private String adverb;
 private String suffix;
 private Pos pos;
 

public void setAdverb(String adverb)
{
 this.adverb=adverb;

}
public String getAdverb()
{
 
 return this.adverb;
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



}
