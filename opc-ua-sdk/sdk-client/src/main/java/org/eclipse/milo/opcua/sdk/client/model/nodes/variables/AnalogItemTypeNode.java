package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.AnalogItemType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class AnalogItemTypeNode extends DataItemTypeNode implements AnalogItemType {
    public AnalogItemTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                              UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                              UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                              double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public Range getInstrumentRange() throws UaException {
        PropertyTypeNode node = getInstrumentRangeNode();
        return (Range) node.getValue().getValue().getValue();
    }

    @Override
    public void setInstrumentRange(Range instrumentRange) throws UaException {
        PropertyTypeNode node = getInstrumentRangeNode();
        node.setValue(new Variant(instrumentRange));
    }

    @Override
    public Range readInstrumentRange() throws UaException {
        try {
            return readInstrumentRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInstrumentRange(Range instrumentRange) throws UaException {
        try {
            writeInstrumentRangeAsync(instrumentRange).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Range> readInstrumentRangeAsync() {
        return getInstrumentRangeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Range) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInstrumentRangeAsync(Range instrumentRange) {
        DataValue value = DataValue.valueOnly(new Variant(instrumentRange));
        return getInstrumentRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public PropertyTypeNode getInstrumentRangeNode() throws UaException {
        try {
            return getInstrumentRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInstrumentRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InstrumentRange", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Range getEuRange() throws UaException {
        PropertyTypeNode node = getEuRangeNode();
        return (Range) node.getValue().getValue().getValue();
    }

    @Override
    public void setEuRange(Range euRange) throws UaException {
        PropertyTypeNode node = getEuRangeNode();
        node.setValue(new Variant(euRange));
    }

    @Override
    public Range readEuRange() throws UaException {
        try {
            return readEuRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEuRange(Range euRange) throws UaException {
        try {
            writeEuRangeAsync(euRange).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Range> readEuRangeAsync() {
        return getEuRangeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Range) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEuRangeAsync(Range euRange) {
        DataValue value = DataValue.valueOnly(new Variant(euRange));
        return getEuRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public PropertyTypeNode getEuRangeNode() throws UaException {
        try {
            return getEuRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEuRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EURange", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public EUInformation getEngineeringUnits() throws UaException {
        PropertyTypeNode node = getEngineeringUnitsNode();
        return (EUInformation) node.getValue().getValue().getValue();
    }

    @Override
    public void setEngineeringUnits(EUInformation engineeringUnits) throws UaException {
        PropertyTypeNode node = getEngineeringUnitsNode();
        node.setValue(new Variant(engineeringUnits));
    }

    @Override
    public EUInformation readEngineeringUnits() throws UaException {
        try {
            return readEngineeringUnitsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEngineeringUnits(EUInformation engineeringUnits) throws UaException {
        try {
            writeEngineeringUnitsAsync(engineeringUnits).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends EUInformation> readEngineeringUnitsAsync() {
        return getEngineeringUnitsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (EUInformation) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEngineeringUnitsAsync(EUInformation engineeringUnits) {
        DataValue value = DataValue.valueOnly(new Variant(engineeringUnits));
        return getEngineeringUnitsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public PropertyTypeNode getEngineeringUnitsNode() throws UaException {
        try {
            return getEngineeringUnitsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEngineeringUnitsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EngineeringUnits", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
