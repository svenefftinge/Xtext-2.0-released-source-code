package org.eclipse.xtext.serializer.sequencer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISyntacticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic;

import com.google.inject.ImplementedBy;

/**
 * The ISequenceParser (1) recursively traverses the semanticRoot, (2) creates a token sequence for each EObject using
 * the ISequencer, (3) uses a state machine to determine the missing grammar elements (such as keywords, unassigned
 * actions and unassigned rule calls), (4) and then feeds all grammar elements and values to the IParseEventsAcceptor.
 * 
 * @see ISequencer is used to create the intial sequence of (grammarElement, Value)-pairs for each EObject.
 * 
 * @see IParseEventsAcceptor is used to capture the output of this algorithm.
 * 
 * @author Moritz Eysholdt - Initial contribution and API
 */
@ImplementedBy(GenericSyntacticSequencer.class)
public interface ISyntacticSequencer {

	public interface ISyntacticSequencerOwner {
		void setSyntacticSequencer(ISyntacticSequencer sequencer);
	}

//	public class PassThroughSyntacticSequencer implements ISyntacticSequencer, ISemanticSequencerOwner {
//
//		protected ISemanticSequencer sequencer;
//
//		@Inject
//		public void setSemanticSequencer(ISemanticSequencer sequencer) {
//			this.sequencer = sequencer;
//		}
//
//		public void createSequence(EObject context, EObject semanticObject, INode previousNode,
//				ISyntacticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
//			sequencer.init(sequenceAcceptor, errorAcceptor);
//			sequencer.createSequence(context, semanticObject);
//		}
//	}

	/**
	 * Serializes the semanticRoot.
	 * 
	 * @param rootRule
	 *            The ParserRule that should be used to serialize semanitcRoot.
	 * @param semanticRoot
	 *            The EObject that should be serialized.
	 * @param constructor
	 *            A handler that accepts a sequence of parse-events and does something useful with it. For example: Mix
	 *            in white-spaces and comments and compose all the tokens to a document.
	 */
	void init(EObject context, EObject semanticObject, ISyntacticSequenceAcceptor sequenceAcceptor,
			ISerializationDiagnostic.Acceptor errorAcceptor);
}