package ddd.model;

import java.beans.PropertyEditor;
import java.util.List;

public class PropertyInfo extends GeneralFeature {

	private static final long serialVersionUID = 1434261078559309665L;

	private List<Feature> propertyFeatures;

	private Class propertyType;

	private Class<PropertyEditor> editorClass;

	private boolean isIndexed;

	private boolean isHashed;

	private Class hashKeyType;

	private boolean isBound;

	private boolean isConstrained;

	private boolean isReadable;

	private boolean isWritable;

	public Class<PropertyEditor> getEditorClass() {
		return editorClass;
	}

	public void setEditorClass(Class<PropertyEditor> editorClass) {
		this.editorClass = editorClass;
	}

	public Class getHashKeyType() {
		return hashKeyType;
	}

	public void setHashKeyType(Class hashKeyType) {
		this.hashKeyType = hashKeyType;
	}

	public boolean isBound() {
		return isBound;
	}

	public void setBound(boolean isBound) {
		this.isBound = isBound;
	}

	public boolean isConstrained() {
		return isConstrained;
	}

	public void setConstrained(boolean isConstrained) {
		this.isConstrained = isConstrained;
	}

	public boolean isHashed() {
		return isHashed;
	}

	public void setHashed(boolean isHashed) {
		this.isHashed = isHashed;
	}

	public boolean isIndexed() {
		return isIndexed;
	}

	public void setIndexed(boolean isIndexed) {
		this.isIndexed = isIndexed;
	}

	public boolean isReadable() {
		return isReadable;
	}

	public void setReadable(boolean isReadable) {
		this.isReadable = isReadable;
	}

	public boolean isWritable() {
		return isWritable;
	}

	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}

	public List<Feature> getPropertyFeatures() {
		return propertyFeatures;
	}

	public void setPropertyFeatures(List<Feature> propertyFeatures) {
		this.propertyFeatures = propertyFeatures;
	}

	public Class getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Class propertyType) {
		this.propertyType = propertyType;
	}

}
