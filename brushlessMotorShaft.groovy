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
	double numberOfBolts =measurments.numberOfBolts
	String boltSizeValue = measurments.shaftMountBoltSize
	for(String key:measurments.keySet()) {
		println "Key "+key+" value "+measurments.get(key)
	}								
	CSG vitamin_capScrew = Vitamins.get("capScrew", boltSizeValue)
									.toZMin()
	CSG boltSet =null;
	double degreesPer = 360/numberOfBolts*2
	for(int i=0;i<numberOfBolts/2;i++) {
		CSG moved = vitamin_capScrew.movex(boltPatternDiameterValue/2)
									.rotz(degreesPer*i)
		if(boltSet==null)
			boltSet=moved
		else
			boltSet=boltSet.union(moved)
	}						
	for(int i=0;i<numberOfBolts/2;i++) {
		CSG moved = vitamin_capScrew.movex(boltPatternDiameterLong/2)
									.rotz(degreesPer*i+90)
		boltSet=boltSet.union(moved)
	}	

	return boltSet
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 