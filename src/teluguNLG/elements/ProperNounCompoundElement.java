package teluguNLG.elements;

public class ProperNounCompoundElement
{
 private String relation;
 private SOCElement firstElement;
 private SOCElement finalElement;

public void setRelation(String relation)
{
 this.relation=relation;
}
public String getRelation()
{
 return this.relation;
}
public void setFirstElement(SOCElement firstElement)
{
 this.firstElement=firstElement;
}
public SOCElement getFirstElement()
{
 return this.firstElement;
}
public void setFinalElement(SOCElement finalElement)
{
 this.finalElement=finalElement;
}
public SOCElement getFinalElement()
{
 return this.finalElement;
}

}
