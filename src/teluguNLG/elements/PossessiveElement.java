package teluguNLG.elements;

public class PossessiveElement
{
 private String type;
 private SOCElement possessor;

public void setType(String type)
{
 this.type=type;
}
public String getType()
{
 return this.type;
}
public void setPossessor(SOCElement possessor)
{
 this.possessor=possessor;
}
public SOCElement getPossessor()
{
 return this.possessor;
}

}
