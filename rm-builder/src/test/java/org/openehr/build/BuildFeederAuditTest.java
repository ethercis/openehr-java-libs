package org.openehr.build;

import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.FeederAuditDetails;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.datatypes.encapsulated.DvEncapsulated;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.PartyRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildFeederAuditTest extends BuildTestBase {


    private String type = "FeederAudit";
    private Map<String, Object> values;
    private RMObject obj;


	public void setUp() throws Exception {
		super.setUp();
		type = "FeederAudit";
		values = new HashMap<String, Object>();
	}
	
	public void tearDown() throws Exception {
		super.tearDown();
		obj = null;
	}
	
	public void testBuildFeederAudit() throws Exception {
    	Map<String, Object> values = new HashMap<String, Object>();

    	List<DvIdentifier> originatingSystemIds = new ArrayList<>();
        List<DvIdentifier> feederSystemItemIds = new ArrayList<>();

    	for (int i = 0; i < 4; i++){
            originatingSystemIds.add(new DvIdentifier("o-issuer-"+i, "o-assigner"+i, "o-"+i, "o-type"+i));
            feederSystemItemIds.add(new DvIdentifier("f-issuer-"+i, "f-assigner"+i, "f"+i, "f-type"+i));
        }

        String aUid = "dfa84168-05d3-11e9-8eb2-f2801f1b9fd1";

        FeederAuditDetails originatingSystemAudit = new FeederAuditDetails(
                "originatingSystemAudit",
                new PartyIdentified((new PartyRef( new HierObjectID(aUid), "type")), "provider", null),
                new PartyIdentified((new PartyRef( new HierObjectID(aUid), "type")), "location", null),
                new DvDateTime("2018-12-18T11:08:00Z"),
                new PartyIdentified((new PartyRef( new HierObjectID(aUid), "type")), "subject", null),
                "version-1");

        FeederAuditDetails feederSystemAudit = new FeederAuditDetails(
                "feederSystemAudit",
                new PartyIdentified((new PartyRef( new HierObjectID(aUid), "type")), "provider", null),
                new PartyIdentified((new PartyRef( new HierObjectID(aUid), "type")), "location", null),
                new DvDateTime("2018-12-18T11:08:00Z"),
                new PartyIdentified((new PartyRef( new HierObjectID(aUid), "type")), "subject", null),
                "version-1");

        DvEncapsulated originalContent = new DvParsable("a_value", "a_formalism111");

        values.put("originatingSystemAudit", originatingSystemAudit);
        values.put("originatingSystemItemIDs", originatingSystemIds);
        values.put("feederSystemAudit", feederSystemAudit);
        values.put("feederSystemItemIDs", feederSystemItemIds);
        values.put("originalContent", originalContent);

        obj = builder.construct(type, values);
        assertTrue(obj instanceof FeederAudit);
        FeederAudit feederAudit = (FeederAudit) obj;
        assertEquals("originatingSystemItemIDs", 4, feederAudit.getOriginatingSystemItemIds().size());
        assertEquals("feederSystemItemIDs", 4, feederAudit.getFeederSystemItemIds().size());

        assertEquals("originatingSystemAudit issuer", "originatingSystemAudit", feederAudit.getOriginatingSystemAudit().getSystemId());
        assertEquals("feederSystemAudit issuer", "feederSystemAudit", feederAudit.getFeederSystemAudit().getSystemId());
        assertEquals("originalContent", "a_value", ((DvParsable)(feederAudit.getOriginalContent())).getValue());
    }

}
