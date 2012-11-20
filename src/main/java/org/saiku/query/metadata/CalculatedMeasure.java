package org.saiku.query.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.olap4j.OlapException;
import org.olap4j.impl.Named;
import org.olap4j.impl.NamedListImpl;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.metadata.Datatype;
import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.Member;
import org.olap4j.metadata.NamedList;
import org.olap4j.metadata.Property;

public class CalculatedMeasure implements Measure, Named, Calculated {


	private Dimension dimension;
	private Hierarchy hierarchy;
	private String name;
	private String uniqueName;
	private String formula;
	
	private Map<Property, Object> properties = new HashMap<Property, Object>();
	private String description;
	private Level level;
	private Datatype datatype;


	public CalculatedMeasure(
			Dimension dimension,
			Hierarchy hierarchy,
			String name,
			String description,
			String formula,
			Map<Property, String> properties)
	{
		this.dimension = dimension;
		this.hierarchy = hierarchy;
		this.level = hierarchy.getLevels().get(0);
		this.name = name;
		this.description = description;
		this.formula = formula;
		this.uniqueName = IdentifierNode.ofNames(hierarchy.getName(), name).toString();
	}
	


	public Dimension getDimension() {
		return dimension;
	}


	public Hierarchy getHierarchy() {
		return hierarchy;
	}
	
	public String getFormula() {
		return formula;
	}

	public Type getMemberType() {
		return Type.FORMULA;
	}


    public Map<Property, Object> getPropertyValueMap() {
        return properties;
    }
    
	@Override
	public NamedList<Property> getProperties() {
		NamedList<Property> p = new NamedListImpl(properties.keySet());
		return p;
		
	}


	public Object getPropertyValue(Property key) throws OlapException {
		if (properties.containsKey(key)) {
			return properties.get(key);
		}
		return null;
	}

	@Override
	public void setProperty(Property key, Object value) throws OlapException {
		properties.put(key, value);
	}


	public String getCaption() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public String getName() {
		return name;
	}


	public String getUniqueName() {
		return uniqueName;
	}


	public Aggregator getAggregator() {
		return Aggregator.CALCULATED;
	}


	public boolean isVisible() {
		return true;
	}



	@Override
	public List<Member> getAncestorMembers() {
		throw new UnsupportedOperationException();
	}



	@Override
	public int getChildMemberCount() throws OlapException {
		throw new UnsupportedOperationException();
	}



	@Override
	public NamedList<? extends Member> getChildMembers() throws OlapException {
		throw new UnsupportedOperationException();
	}



	@Override
	public Member getDataMember() {
		throw new UnsupportedOperationException();
	}



	@Override
	public int getDepth() {
		throw new UnsupportedOperationException();
	}



	@Override
	public ParseTreeNode getExpression() {
		throw new UnsupportedOperationException();
	}



	@Override
	public Level getLevel() {
		return level;
	}



	@Override
	public int getOrdinal() {
		throw new UnsupportedOperationException();
	}



	@Override
	public Member getParentMember() {
		throw new UnsupportedOperationException();
	}



	@Override
	public String getPropertyFormattedValue(Property property) throws OlapException {
		return String.valueOf(getPropertyValue(property));
	}



	@Override
	public int getSolveOrder() {
		throw new UnsupportedOperationException();
	}



	@Override
	public boolean isAll() {
		return false;
	}



	@Override
	public boolean isCalculated() {
		return true;
	}



	@Override
	public boolean isCalculatedInQuery() {
		return true;
	}



	@Override
	public boolean isChildOrEqualTo(Member arg0) {
		return false;
	}



	@Override
	public boolean isHidden() {
		return false;
	}



	@Override
	public Datatype getDatatype() {
	        if (datatype  != null) {
	        	return datatype;
	        }
	        return Datatype.STRING;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((uniqueName == null) ? 0 : uniqueName.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculatedMeasure other = (CalculatedMeasure) obj;
		if (uniqueName == null) {
			if (other.uniqueName != null)
				return false;
		} else if (!uniqueName.equals(other.uniqueName))
			return false;
		return true;
	}
	

}