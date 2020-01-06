import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "brushlessMotorShaft"
	if(args==null)
		args=["TL2954"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def boltPatternDiameterValue = measurments.boltPatternDiameter
	def numberOfBoltsValue = measurments.numberOfBolts
	def boltPatternDiameterLong =measurments.boltPatternDiameterLong
	println "Loaded from vitamins measurments boltPatternDiameterValue:  "+boltPatternDiameterValue+" value is = "+boltPatternDiameterValue
	println "Loaded from vitamins measurments numberOfBoltsValue:  "+numberOfBoltsValue+" value is = "+numberOfBoltsValue
	// Stub of a CAD object
	CSG part = new Cube().toCSG()
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 