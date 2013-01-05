package com.umansoor.util.Version;

import static org.junit.Assert.*;
import  org.junit.*;
import com.umansoor.util.Version.Version;

import org.junit.Test;

public class VersionTest {

	
	
	@Before
	public void setUp() {
		
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testCreatingFromString() {
		Version version;
		
		// Null values are not accepted.
		try {
			Version.valueOf(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		// Bogus string values are also not accepted
		try {
			Version.valueOf("wrong");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		// At least major and minor versions are required.
		try {
			Version.valueOf("1.");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		// A non number value for minor version should fail.
		try {
			Version.valueOf("1.w");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			
		}
		
		// A non number value for build version should fail.
		try {
			Version.valueOf("1.2.w");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			Version.valueOf("1.2.w.4");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
		version = Version.valueOf("2.3.4.1");
		assertEquals("Result", 2, version.getMajor().intValue());
		assertEquals("Result", 3, version.getMinor().intValue());
		assertEquals("Result", 4, version.getRevision().intValue());
		assertEquals("Result", 1, version.getBuild().intValue());
		assertTrue("2.3.4.1".equals(version.toString()));
		} catch (Exception e) {
			fail("This exception shouldn't have been thrown.");
		}
	}
	
	@Test
	public void testBogusInitializers() {
		try {
			Version.valueOf(null,null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			Version.valueOf(1,null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			Version.valueOf(1,3,null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			Version.valueOf(1,4,5,null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	@Test
	public void testToString() {
		Version version = Version.valueOf(1, 3); //version 1.3.0.0
		assertEquals("Result", "1.3.0.0", version.toString());
		version = Version.valueOf(1, 5, 4345); //version 1.5.0.4345
		assertEquals("Result", "1.5.0.4345", version.toString());
		version = Version.valueOf(1,5,6,6543); //1.5.6.6543
		assertEquals("Result", "1.5.6.6543", version.toString());
	}
	
	@Test
	public void testEquality() {
		// Declare variables used in this test.
		Version version1;
		Version version2;
		Version version3;
		
		// Test with major and minor only.
		version1 = Version.valueOf(1, 2); //version 1.2.0.0
		version2 = Version.valueOf(1, 2); //version 1.2.0.0
		version3 = Version.valueOf(1,3); //1.3.0.0 
		assertTrue( version1.equals(version2));
		assertFalse(version1.equals(version3));
		
		//Test with major, minor and build.
		version1 = Version.valueOf(1,3,4567); //1.3.0.4567
		version2 = Version.valueOf(1,3,4567); //1.3.0.4567
		version3 = Version.valueOf(1,3,4568); //1.3.0.4568
		assertTrue(version1.equals(version2));
		assertFalse(version1.equals(version3));
		
		//Test with major, minor, revision and build.
		version1 = Version.valueOf(1,4,3,2567); //1,4,3,2567
		version2 = Version.valueOf(1,4,3,2567); //1,4,3,2567
		version3 = Version.valueOf(1,4,2,2567); //1,4,2,2567
		assertTrue(version1.equals(version2));
		assertFalse(version1.equals(version3));
		
	}
	
	@Test
	public void testComparability() {
		// Declare variables used in this test.
		Version version1;
		Version version2;
		Version version3;
				
		// Test with major and minor only.
		version1 = Version.valueOf(1,2); //version 1.2.0.0
		version2 = Version.valueOf(1,2); //version 1.2.0.0
		version3 = Version.valueOf(1,3); //1.3.0.0 
		assertEquals("Result", 0, version1.compareTo(version2));
		assertEquals("Result", 1, version3.compareTo(version1));
		assertEquals("Result", -1, version1.compareTo(version3));
		
		// Test with major, minor and build.
		version1 = Version.valueOf(1, 2, 6231); //1.2.0.6231
		version2 = Version.valueOf(1, 2, 6231); //1.2.0.6231
		version3 = Version.valueOf(1, 2, 6234); //1.2.0.6234
		assertEquals("Result", 0, version1.compareTo(version2));
		assertEquals("Result", 1, version3.compareTo(version1));
		assertEquals("Result", -1, version1.compareTo(version3));

		
		// Test with major, minor revision and build.
		version1 = Version.valueOf(1, 2, 3, 6231); //1.2.3.6231
		version2 = Version.valueOf(1, 2, 3, 6231); //1.2.3.6231
		version3 = Version.valueOf(1, 2, 4, 6231); //1.2.4.6231
		assertEquals("Result", 0, version1.compareTo(version2));
		assertEquals("Result", 1, version3.compareTo(version1));
		assertEquals("Result", -1, version1.compareTo(version3));
	}
	
	@Test
	public void testHashcode() {
		// Declare variables used in this test.
		Version version1;
		Version version2;
		Version version3;
						
		// Test with major and minor only.
		version1 = Version.valueOf(1,2); //version 1.2.0.0
		version2 = Version.valueOf(1,2); //version 1.2.0.0
		version3 = Version.valueOf(1,3); //1.3.0.0 
		
		if (version1.equals(version2))
			assertTrue(version1.hashCode() == version2.hashCode());
		else
			assertFalse(version1.hashCode() == version2.hashCode());
		
		if (version1.equals(version3))
			assertTrue(version1.hashCode() == version3.hashCode());
		else
			assertFalse(version1.hashCode() == version3.hashCode());
		
		// Test with major, minor and build.
		version1 = Version.valueOf(1, 2, 6231); //1.2.0.6231
		version2 = Version.valueOf(1, 2, 6231); //1.2.0.6231
		version3 = Version.valueOf(1, 2, 6234); //1.2.0.6234
		
		if (version1.equals(version2))
			assertTrue(version1.hashCode() == version2.hashCode());
		else
			assertFalse(version1.hashCode() == version2.hashCode());
		
		if (version1.equals(version3))
			assertTrue(version1.hashCode() == version3.hashCode());
		else
			assertFalse(version1.hashCode() == version3.hashCode());
		
		// Test with major, minor revision and build.
		version1 = Version.valueOf(1, 2, 3, 6231); //1.2.3.6231
		version2 = Version.valueOf(1, 2, 3, 6231); //1.2.3.6231
		version3 = Version.valueOf(1, 2, 4, 6231); //1.2.4.6231
		
		if (version1.equals(version2))
			assertTrue(version1.hashCode() == version2.hashCode());
		else
			assertFalse(version1.hashCode() == version2.hashCode());
		
		if (version1.equals(version3))
			assertTrue(version1.hashCode() == version3.hashCode());
		else
			assertFalse(version1.hashCode() == version3.hashCode());
				
		
		
	}
	

}
