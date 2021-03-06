Java openEHR Implementation project
===================================

This fork of https://github.com/openEHR/java-libs.git contains all changes
required for the EtherCIS project.

Changelog
=========

CHC, 2018-04-10 This is the last adaptation to use with EtherCIS 1.1.2+ with all changes merged from
  the previous 1.1.1. This removes dependencies as found previously in ehrservice, core module.

1.0.14-ec1 (2017-06-20)
-----------------------

* up to date with ethercis ehrservice 191a40e (2017-06-14)
* created this fork and setup gitignore (RVE)
* [oet-parser] made Flattener#flattenItem recurse into nested items (CHC)
* merged all changes from openehr-am-rm-term-1.0.9.jar and ehrserver (RVE):
  * ArchetypeOntology constructor takes an extra `languages` argument
  * Added a couple of units to SimpleMeasurementService
  * Added the JaxbUtil, CArchetypeRoot, ExistenceAdapter, IntervalDateAdapter,
    IntervalDateTimeAdapter, IntervalDurationAdapter, IntervalIntegerAdapter, IntervalRealAdapter,
    IntervalTimeAdapter, Interval, IntervalOfDate, IntervalOfDateTime, IntervalOfDuration,
    IntervalOfInteger, IntervalOfReal, IntervalOfTime, class
  * Uppercased the texts for BOOLEAN, REAL, INTEGER, STRING and ARCHETYPE in ExpressionItem
  * Added XML bindings to ArchetypeConstraint, CDomainType, CObject, TermBindingItem
  * For various model classes:
    * added no argument constructors
    * made some fields non final so they can be constructed using no argument constructors
    * made various fields more accessible (adding public setters)
  * In Locatable#processPredicate ignore eveything but the value of a DvCodedText
  * Made the compressionAlgorithm and integrityCheckAlgorithm arguments of DvMultimedia optional
    (according to the ref they should be optional)
  * Made changes to that "\n" and "\r" are allowed in DvText
  * Removed pattern validation from InternetID so it accepts anything (this violates the spec which
    only allows reverse internet domains)
  * Added the `namespace` argument to the PartyRef class which defaults to `DEMOGRAPHIC`
  * Added the `cause` argument to various exception constructors
  * Added some methods to RMObjectBuilder (but not all of the changes found in openehr-am-rm-term
    and ehrserver, this is still a TODO)
  * Moved all XMLBeans classes to the xml-beans module to avoid class loader issues



(to update the version: `mvn versions:set -DnewVersion=1.0.14-ec?-SNAPSHOT`)
