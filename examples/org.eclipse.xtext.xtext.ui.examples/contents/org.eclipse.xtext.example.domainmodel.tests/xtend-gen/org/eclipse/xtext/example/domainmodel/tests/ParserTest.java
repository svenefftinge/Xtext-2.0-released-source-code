package org.eclipse.xtext.example.domainmodel.tests;

import com.google.inject.Inject;
import junit.framework.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.example.domainmodel.domainmodel.AbstractElement;
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel;
import org.eclipse.xtext.example.domainmodel.domainmodel.Entity;
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature;
import org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration;
import org.eclipse.xtext.example.domainmodel.domainmodel.Property;
import org.eclipse.xtext.example.domainmodel.tests.InjectorProviderCustom;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(InjectorProviderCustom.class)
public class ParserTest {
  
  @Inject
  private ParseHelper<DomainModel> parser;
  
  @Test
  public void testParsing() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package example {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("entity MyEntity {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("property : String");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      DomainModel _parse = this.parser.parse(_builder);
      final DomainModel model = _parse;
      EList<AbstractElement> _elements = model.getElements();
      AbstractElement _get = _elements.get(0);
      final PackageDeclaration pack = ((PackageDeclaration) _get);
      String _name = pack.getName();
      Assert.assertEquals("example", _name);
      EList<AbstractElement> _elements_1 = pack.getElements();
      AbstractElement _get_1 = _elements_1.get(0);
      final Entity entity = ((Entity) _get_1);
      String _name_1 = entity.getName();
      Assert.assertEquals("MyEntity", _name_1);
      EList<Feature> _features = entity.getFeatures();
      Feature _get_2 = _features.get(0);
      final Property property = ((Property) _get_2);
      String _name_2 = property.getName();
      Assert.assertEquals("property", _name_2);
      JvmParameterizedTypeReference _type = property.getType();
      String _identifier = _type.getIdentifier();
      Assert.assertEquals("java.lang.String", _identifier);
    }
  }
}